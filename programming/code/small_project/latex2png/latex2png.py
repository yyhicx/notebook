# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Convert LaTeX mathematical expressions to PNG."""
import matplotlib.pyplot as plt
import io
from PIL import Image, ImageChops

background_color = (214, 214, 214, 255)


def latex2png(math_exp, img_size=(5,3), font_size=16):
  buf = io.BytesIO()
  fig = plt.figure(figsize=img_size)
  ax = fig.add_axes([0, 0, 1, 1])
  ax.get_xaxis().set_visible(False)
  ax.get_yaxis().set_visible(False)
  ax.set_xticks([])
  ax.set_yticks([])
  plt.rcParams['font.sans-serif'] = ['SimHei']
  plt.text(0.5, 0.5, math_exp, fontsize=font_size,
           verticalalignment='center', horizontalalignment='center')
  plt.savefig(buf, format='png')
  plt.close()

  im = Image.open(buf)
  bg = Image.new(im.mode, im.size, background_color)
  diff = ImageChops.difference(im, bg)
  diff = ImageChops.add(diff, diff, 2.0, -100)
  bbox = diff.getbbox()
  return im.crop(bbox)


if __name__ == '__main__':
  str_latex = r'$10^x$'
  img = latex2png(str_latex, img_size=(1,1), font_size=32)
  img.save('img.png')
