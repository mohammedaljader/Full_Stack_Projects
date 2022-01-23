import os
import glob


def clean():
    files = glob.glob('op_files/*')
    for f in files:
        os.remove(f)