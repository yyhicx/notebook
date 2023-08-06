"""Create multiple UML diagrams with PlantUML

Input: filenames in yaml file & filenams under txt folder

Run PlatnUML, commands like java -jar ./plantuml.jar

Ouput: store all output in the png folder
"""

import hashlib
import os

import yaml

def main():
    text_folder_path = './txt'
    png_folder_path = '../png'
    yaml_file_path = './file.yaml'

    text_file_names = os.listdir(text_folder_path)

    file_names_and_md5_value = []
    for file_name in text_file_names:
        with open(os.path.join(text_folder_path, file_name), 'rb') as fp:
            data = fp.read()
        md5_value = hashlib.md5(data).hexdigest()
        file_names_and_md5_value.append({file_name: md5_value})

    new_yaml_file_data = []
    new_png_file_data = []
    with open(yaml_file_path, 'r') as fp:
        yaml_file_data = yaml.load(fp, Loader=yaml.FullLoader)
        for l1 in yaml_file_data:
            nl1 = dict()
            for k1, v1 in l1.items():
                nv1 = []
                for l2 in v1:
                    new_fn, m = new_fnamv(l2, file_names_and_md5_value)
                    if m:
                        for fn in new_fn.keys():
                            new_png_file_data.append(fn)
                    nv1.append(new_fn)
                nl1 = {k1: nv1}
            new_yaml_file_data.append(nl1)
    
    with open(yaml_file_path, 'w') as f:
        yaml.dump(new_yaml_file_data, f)
            
    # java -jar ./plantuml.jar filename.txt -o imagename.png
    for npfd in new_png_file_data:
        npfd = os.path.join(text_folder_path, npfd)
        os.system('java -jar ./plantuml.jar ' + npfd + ' -o ' + png_folder_path)
        print(npfd)


def new_fnamv(ds, fnamv):
    m = False
    for k1, v1 in ds.items():
        for fn in fnamv:
            for k2, v2 in fn.items():
                if (k1 == k2):
                    if (v1 != v2):
                        v1 = v2
                        m = True
                    return {k1: v1}, m
    return ds, m


if __name__ == '__main__':
    main()
