# ffmpeg

## Windows

Install:

*   open <https://ffmpeg.org/download.html> and download
*   copy `ffmpeg\bin` to `environment variables/Path`

Run:

```bash
# Format conversion
ffmpeg -i input.fmt1 output.fmt2
# Extract a 10-second video starting from 30s and package it into out.mp4 with h264 video codec and aac audio codec
ffmpeg -i in.mp4 -ss 00:00:30 -t 00:00:10 -acodec aac -vcodec h264 -acodec aac out.mp4
```
