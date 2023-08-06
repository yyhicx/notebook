# you-get

## Windows

Install: pip3 install you-get

Run:

```bash
# 查看所有可用画质与格式
you-get -i url_path
# 下载
you-get url_path
# 下载多任务
you-get --playlist url_path
# 下载指定格式，itag_number 需要通过 -i 命令查看
you-get --itag=itag_number url_path
# 下载并设置输出文件名和输出路径
you-get -o ouput_dir -O output_filename url_path
```
