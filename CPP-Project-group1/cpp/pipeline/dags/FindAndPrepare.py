import os
import glob
import pandas as pd
from airflow.operators.python_operator import PythonOperator
import DataAccess

def readCsvAndSave():
    rootdir = 'ip_files'
    dfs_media = []
    dfs_image = []
    dfs_print = []
    new_traversed = []
    traversed = DataAccess.getTraversedPath()
    print(traversed)
    # Traverse root folder to find all csv files by type
    for subdir, dirs, files in os.walk(rootdir):
        for file in files:
            file_name = subdir + "/" + file
            if file.endswith(".csv") and file_name not in traversed:
                print(file_name)
                print(file_name in traversed) 
                if file.startswith("Image") and "engineControl/accounting/image" in subdir:  
                    if(file_name not in new_traversed):
                        new_traversed.append(file_name)
                        dfs_image.append(read_csv(subdir, rootdir, file))
                elif file.startswith("MediaPrepare") and "engineControl/MediaPrepare" in subdir:
                    if(file_name not in new_traversed):
                        new_traversed.append(file_name) 
                        dfs_media.append(read_csv(subdir, rootdir, file))
                elif file.startswith("PrintCycle") and "engineControl/PrintCycle" in subdir:
                    if(file_name not in new_traversed):
                        new_traversed.append(file_name)
                        dfs_print.append(read_csv(subdir, rootdir, file))

    for subdir, dirs, files in os.walk(rootdir):
        if "image" in subdir or "MediaPrepare" in subdir or "PrintCycle" in subdir:
            list_of_files = glob.glob(subdir + "/*") # * means all if need specific format then *.csv
            latest_file = max(list_of_files, key=os.path.getctime)
            print(latest_file)
            if len(new_traversed) > 0 and latest_file in new_traversed:
                 new_traversed.remove(latest_file)
            

    if(len(new_traversed)>0):
        DataAccess.insertTraversedPath(new_traversed)
    if(len(dfs_media)>0 and len(dfs_image)>0 and len(dfs_print)>0):
        process(dfs_media).to_csv('op_files/result_dfs_media.csv.gz', compression='gzip')
        process(dfs_image).to_csv('op_files/result_dfs_image.csv.gz', compression='gzip')
        process(dfs_print).to_csv('op_files/result_dfs_print.csv.gz', compression='gzip')
   


def findMachineID(subdir, rootdir):
    # Remove root dir from string
    withoutroot = subdir.replace(rootdir, "")
    output = ""
    counter = 0
    # Go through each character
    for x in range(0, len(withoutroot)):
        if counter < 2:
            # Check for backslash and iterate counter to get first dir
            if withoutroot[x] == "/":
                counter = counter + 1
            else:
                output = output + withoutroot[x]
    return output


def process(dataframe):
    # Fix dataframe
    result = pd.concat(dataframe)
    result.columns = result.columns.str.replace('$', '')
    result.columns = result.columns.str.replace('#', '')
    result.columns = result.columns.str.replace('~', '')
    return result


def read_csv(subdir, rootdir, file):
    df = pd.read_csv(os.path.join(subdir, file), sep=";")
    df['Machine_id'] = findMachineID(subdir, rootdir)
    return df
