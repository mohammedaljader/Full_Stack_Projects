import mysql.connector
from mysql.connector import Error
import pandas as pd
import datetime
import os


def insertInkOverTime():
    connection = mysql.connector.connect(host='studmysql01.fhict.local',
                                         database='dbi454066',
                                         user='dbi454066',
                                         password='L-khb}_2EVng+*SG')

    if os.path.isfile('op_files/inkdateTotal1D.csv.gz'):
        df = pd.read_csv('op_files/inkdateTotal1D.csv.gz', compression='gzip')
        if connection.is_connected():
            db_Info = connection.get_server_info()
            cursor = connection.cursor(buffered=True)
            cursor.execute("select database();")

            for row in df.itertuples():
                converted = datetime.datetime.strptime(row.date, "%Y-%m-%d")
                cursor.execute('''
                            INSERT INTO ink_usage_over_time (`machine_id`, `date`, `color_black`, `color_cyan`, `color_magenta`, `color_yellow`)
                            VALUES (%s,%s,%s,%s,%s,%s)
                            ''',
                               (row.Machine_id,
                                converted,
                                row.Black,
                                row.Cyan,
                                row.Magenta,
                                row.Yellow
                                ))

            connection.commit()
            connection.close()


def insertMedia():
    connection = mysql.connector.connect(host='studmysql01.fhict.local',
                                         database='dbi454066',
                                         user='dbi454066',
                                         password='L-khb}_2EVng+*SG')
    if os.path.isfile('op_files/mediaCategories.csv.gz'):
        df = pd.read_csv('op_files/mediaCategories.csv.gz', compression='gzip')
        if connection.is_connected():
            db_Info = connection.get_server_info()
            cursor = connection.cursor(buffered=True)
            cursor.execute("select database();")
            df.columns = df.columns.str.replace(' ', '_')
            df.columns = df.columns.str.replace('/', '_')
            for row in df.itertuples():
                converted = datetime.datetime.strptime(row.date, "%Y-%m-%d")
                cursor.execute('''
                                       INSERT INTO `media_category_over_time` (`machine_id`, `date`, `Paper`, `LightPaper`, `HeavyPaper`,
                                       `Film`, `ThickFilm`, `LightBanner`, `HeavyBanner`, `Textile`, `MonomericVinyl`, `Canvas`, `PolymericCastVinyl`)
                                       VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);  
                                       ''',
                               (row.Machine_id,
                                converted,
                                row.Paper,
                                row.Light_Paper,
                                row.Heavy_Paper,
                                row.Film,
                                row.Thick_Film,
                                row.Light_Banner,
                                row.Heavy_Banner,
                                row.Textile,
                                row.Monomeric_Vinyl,
                                row.Canvas,
                                row.Polymeric___cast_Vinyl,
                                ))

            connection.commit()
            connection.close()


def insertSqmPrintMode():
    connection = mysql.connector.connect(host='studmysql01.fhict.local',
                                         database='dbi454066',
                                         user='dbi454066',
                                         password='L-khb}_2EVng+*SG')
    if os.path.isfile('op_files/sqmPrintMode.csv.gz'):
        df = pd.read_csv('op_files/sqmPrintMode.csv.gz', compression='gzip')
        if connection.is_connected():
            db_Info = connection.get_server_info()
            cursor = connection.cursor(buffered=True)
            cursor.execute("select database();")
            df = df.add_prefix('col_')
            for row in df.itertuples():
                converted = datetime.datetime.strptime(row.col_date, "%Y-%m-%d")
                cursor.execute('''
                                       INSERT INTO `sqm_per_print_mode` (`machine_id`, `date`, `1_pass`, `1_pass_highDensity`, `2_pass`, `4_pass`, `8_pass`, `8_pass_highDensity`, `16_pass`, `other`) 
                                       VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s); 
                                       ''',
                               (row.col_Machine_id,
                                converted,
                                row.col_1_pass,
                                row.col_1_pass_highDensity,
                                row.col_2_pass,
                                row.col_4_pass,
                                row.col_8_pass,
                                row.col_8_pass_highDensity,
                                row.col_16_pass,
                                row.col_others,
                                ))

            connection.commit()
            connection.close()


def insertUsedMediaType():
    connection = mysql.connector.connect(host='studmysql01.fhict.local',
                                         database='dbi454066',
                                         user='dbi454066',
                                         password='L-khb}_2EVng+*SG')
    if os.path.isfile('op_files/usedMedia_temp.csv.gz'):
        df = pd.read_csv('op_files/usedMedia_temp.csv.gz', compression='gzip',
                         names=['Machine_id', 'MediaTypeDisplayName', 'SquareDecimeter'])
        if connection.is_connected():
            print(df.columns.values.tolist())
            db_Info = connection.get_server_info()
            cursor = connection.cursor(buffered=True)
            cursor.execute("select database();")
            for row in df.itertuples():
                print(row.Machine_id)
                print(row.MediaTypeDisplayName)
                print(row.SquareDecimeter)
                cursor.execute('''
                                       INSERT INTO `used_media_type` (`machine_id`,`name`,`area`) 
                                       VALUES (%s,%s,%s); 
                                       ''',
                               (row.Machine_id,
                                row.MediaTypeDisplayName,
                                row.SquareDecimeter,
                                ))

            connection.commit()
            connection.close()


def getTraversedPath():
    mydb = mysql.connector.connect(host='studmysql01.fhict.local',
                                   database='dbi454066',
                                   user='dbi454066',
                                   password='L-khb}_2EVng+*SG')

    mycursor = mydb.cursor(buffered=True)

    mycursor.execute("select database();")
    mycursor.execute("SELECT path FROM `traversed`;")

    myresult = mycursor.fetchall()
    res = []
    for path in myresult:
        res.append(path[0])
    mydb.close()
    return res


def insertTraversedPath(path_list):
    connection = mysql.connector.connect(host='studmysql01.fhict.local',
                                         database='dbi454066',
                                         user='dbi454066',
                                         password='L-khb}_2EVng+*SG')

    if connection.is_connected():
        db_Info = connection.get_server_info()
        cursor = connection.cursor(buffered=True)
        cursor.execute("select database();")

        for path in path_list:
            cursor.execute("INSERT INTO `traversed` (`path`) VALUES ('" + path + "')")

        connection.commit()
        connection.close()