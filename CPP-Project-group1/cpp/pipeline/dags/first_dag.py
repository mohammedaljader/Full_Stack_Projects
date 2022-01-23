try:
    from datetime import timedelta
    from airflow import DAG
    from airflow.operators.python_operator import PythonOperator
    from datetime import datetime
    import pandas as pd
    from DataAccess import insertInkOverTime
    from DataAccess import insertMedia
    from FindAndPrepare import readCsvAndSave
    from Aggregate import aggregateInkUsage
    from Aggregate import aggregateMediaCategories
    from CleanTempStorage import clean
    from DataAccess import insertSqmPrintMode
    from Aggregate import aggregateSqmPerPrintMode
    from Aggregate import aggregateUsedMedia
    from DataAccess import insertUsedMediaType

    print("All Dag modules are ok ......")
except Exception as e:
    print("Error  {} ".format(e))

with DAG(
        dag_id="first_dag",
        schedule_interval="@daily",
        default_args={
            "owner": "airflow",
            "retries": 1,
            "retry_delay": timedelta(minutes=5),
            "start_date": datetime(2021, 1, 1),
        },
        catchup=False) as f:

    read = PythonOperator(
        task_id="readcsv",
        python_callable=readCsvAndSave
    )

    aggregate_ink_usage = PythonOperator(
        task_id ="aggregate_ink_usage",
        python_callable = aggregateInkUsage
    )

    aggregateMediaCategories = PythonOperator(
        task_id ="aggregateMediaCategories",
        python_callable = aggregateMediaCategories
    )

    addInk = PythonOperator(
        task_id="addInk",
        python_callable=insertInkOverTime
    )

    addMedia = PythonOperator(
        task_id="addMedia",
        python_callable=insertMedia
    )

    cleanTempStorage = PythonOperator(
        task_id="cleanTempStorage",
        python_callable=clean
    )

    addSqmPrintMode = PythonOperator(
        task_id="addSqmPerPrintMode",
        python_callable=insertSqmPrintMode
    )

    aggregateSqmPrintMode = PythonOperator(
        task_id="aggregateSqmPerPrintMode",
        python_callable=aggregateSqmPerPrintMode
    )

    aggregateUsedMedia = PythonOperator(
        task_id="aggregateUsedMedia",
        python_callable=aggregateUsedMedia
    )

    addUsedMediaType = PythonOperator(
        task_id="addUsedMediaType",
        python_callable=insertUsedMediaType
    )

read>>aggregateUsedMedia>>addUsedMediaType>>cleanTempStorage
read>>aggregate_ink_usage>>addInk>>cleanTempStorage
read>>aggregateMediaCategories>>addMedia>>cleanTempStorage
read>>aggregateSqmPrintMode>>addSqmPrintMode>>cleanTempStorage





