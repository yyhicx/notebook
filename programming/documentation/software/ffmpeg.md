# ffmpeg

## Windows

Install:

*   open <https://ffmpeg.org/download.html> and download
*   copy `ffmpeg\bin` to `environment variables/Path`

Run:

```bash
# 格式转换
ffmpeg -i input.fmt1 output.fmt2
# 从 30s 开始截取 10 秒的视频并封装进 h264，aac 编码的 out.mp4 里
ffmpeg -i in.mp4 -ss 00:00:30 -t 00:00:10 -acodec aac -vcodec h264 -acodec aac out.mp4
```
