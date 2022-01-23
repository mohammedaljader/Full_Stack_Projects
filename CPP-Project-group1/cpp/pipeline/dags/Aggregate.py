import pandas as pd
import os

def aggregateInkUsage():
    if os.path.isfile('op_files/result_dfs_image.csv.gz'):
        imageDF = pd.read_csv('op_files/result_dfs_image.csv.gz', compression='gzip')

        inkImageDf = imageDF.drop(
            columns=['ullid', 'ImageLengthAnnounced[m]', 'ImageLength[m]', 'ImageWidth[m]', 'ImageId', 'EngineCycleId',
                     'LocalTime[us]'])
        index = pd.to_datetime(inkImageDf[['date']].apply(' '.join, 1), dayfirst=True)
        index.rename("date")
        # #column for every 6 hours
        inkdateTotal6H = inkImageDf.groupby(['Machine_id', index])[
            'AccountedInkBlack[ml]', 'AccountedInkCyan[ml]', 'AccountedInkMagenta[ml]', 'AccountedInkYellow[ml]'].sum()
        inkdateTotal6H = inkdateTotal6H.rename(
            {'AccountedInkBlack[ml]': 'Black', 'AccountedInkCyan[ml]': 'Cyan', 'AccountedInkMagenta[ml]': 'Magenta',
             'AccountedInkYellow[ml]': 'Yellow'}, axis=1)

        inkdateTotal6H.to_csv('ink.csv.gz', compression='gzip')

        new_df = pd.read_csv('ink.csv.gz', compression='gzip')

        new_df.rename(columns={new_df.columns[1]: 'date'}, inplace=True)

        new_df.to_csv('op_files/inkdateTotal1D.csv.gz', compression='gzip')

def aggregateMediaCategories():
    if os.path.isfile('op_files/result_dfs_image.csv.gz'):
        imageDF = pd.read_csv('op_files/result_dfs_image.csv.gz', compression='gzip')
        imageDF['ImageArea'] = imageDF['ImageLength[m]'] * imageDF['ImageWidth[m]']
        df1 = imageDF.groupby(['Machine_id','date', 'MediaType'])['ImageArea'].sum().unstack('MediaType')
        cols=['Paper','Light Paper','Heavy Paper','Film','Thick Film','Light Banner','Heavy Banner','Textile','Monomeric Vinyl','Canvas','Polymeric / cast Vinyl']
        df1.fillna(0, inplace=True, downcast='infer')
        for x in cols:
            if x not in df1:
                df1[x] = 0
        df1.to_csv('op_files/mediaCategories.csv.gz', compression='gzip')

def aggregateSqmPerPrintMode():
    if os.path.isfile('op_files/result_dfs_print.csv.gz'):
        printCycleDF = pd.read_csv('op_files/result_dfs_print.csv.gz', compression='gzip')
        df2 = printCycleDF.groupby(['Machine_id','date', 'PrintMode'])['SquareDecimeter[dm2]'].sum().unstack('PrintMode') 
        df2.fillna(0, inplace=True, downcast='infer')
        cols=['1_pass','1_pass_highDensity', '2_pass', '4_pass', '8_pass', '8_pass_highDensity', '16_pass', 'others']
    
        for x in cols:
            if x not in df2:
                 df2[x] = 0

        to_delete = []
        df2["others"]=0
        for column in enumerate(df2.columns):
            if column[1] not in cols:
                df2["others"] = df2["others"] + df2[column[1]]
                to_delete.append(column[1])

        df_res = df2.drop(columns=to_delete)
        df_res.to_csv('op_files/sqmPrintMode.csv.gz', compression='gzip')


def aggregateUsedMedia():
    if os.path.isfile('op_files/result_dfs_print.csv.gz') and os.path.isfile('op_files/result_dfs_media.csv.gz'):
        printCycleDF = pd.read_csv('op_files/result_dfs_print.csv.gz', compression='gzip')
        mediaDF = pd.read_csv('op_files/result_dfs_media.csv.gz', compression='gzip')
        usedMediaDF = pd.merge(printCycleDF, mediaDF, on='EngineCycleId', how='inner')
        print(usedMediaDF.columns.values.tolist())
        df = usedMediaDF.groupby(['Machine_id_y','MediaTypeDisplayName'])['SquareDecimeter[dm2]'].sum()
        df.to_csv('op_files/usedMedia_temp.csv.gz', compression='gzip')

       

