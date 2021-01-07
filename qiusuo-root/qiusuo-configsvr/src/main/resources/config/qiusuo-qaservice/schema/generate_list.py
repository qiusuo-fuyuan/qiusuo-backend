# -*- coding: utf-8 -*-

"""
This file is used to generate resource file list.
Default target file name is "application" + <your resource suffix>,
E.g. your resource file is "auth.graphqls", the target file name will be "application.graphqls".
How to use:
1, put this file under your resource folder.
2, run like: python generate_list.py --suffix=graphqls, then it will detect your resource which file extension is ".graphqls".
"""
import os
import optparse

CURRENT_PATH = os.path.dirname(os.path.abspath(__file__))
BASE_FILE = os.path.basename(__file__)


def start_generate(target=None, suffix=None):
    default_generate_file = "application." + suffix
    generate_file = target if target is not None else default_generate_file

    list_dir = os.listdir(CURRENT_PATH)
    list_dir.remove(BASE_FILE)

    if generate_file in list_dir:
        list_dir.remove(generate_file)

    if len(list_dir) == 0:
        print("No resource file found...")
        exit(-1)

    count = 0
    generate_file_absolute_path = CURRENT_PATH + "/" + generate_file
    print("start find resource...")
    with open(generate_file_absolute_path, "w") as f:
        for resource in list_dir:
            if resource != generate_file and resource.endswith("." + suffix):
                count += 1
                print("find: " + resource)
                f.write(resource + "\n")

    print("total found {0} resource files.".format(count))
    print("generate file: {0}".format(generate_file_absolute_path))


if __name__ == '__main__':
    parser = optparse.OptionParser()
    parser.add_option("", "--suffix", dest="suffix",
        help="the resource suffix, e.g. your resource file is test.yml, you can can input yml")
    parser.add_option("", "--target", dest="target",  help="the target you want generate.")

    options, args = parser.parse_args()

    if options.suffix is None:
        parser.error("you should specify the suffix, run like: python generate_list.py --suffix=graphqls")

    start_generate(options.target, options.suffix)
