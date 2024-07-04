# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
# https://stackoverflow.com/questions/72749874/how-to-create-rotated-text-in-pythons-turtle-graphics
"""Patch for the Tuttle library."""


class PatchTxtAngle:
  """Text writing patch to support rotation."""

  def RawTurtleDOTwrite(self,
                        arg,
                        move=False,
                        align='left',
                        font=('Arial', 10, 'normal'),
                        txt_angle=0):
    if self.undobuffer:
      self.undobuffer.push(['seq'])
      self.undobuffer.cumulate = True
    end = self._write(str(arg), align.lower(), font, txt_angle)
    if move:
      _, y = self.pos()
      self.setpos(end, y)
    if self.undobuffer:
      self.undobuffer.cumulate = False

  def RawTurtleDOT_write(self, txt, align, font, txt_angle):
    # pylint: disable=protected-access
    item, end = self.screen._write(self._position, txt, align, font,
                                   self._pencolor, txt_angle)
    self.items.append(item)
    if self.undobuffer:
      self.undobuffer.push(('wri', item))
    return end

  def TurtleScreenBaseDOT_write(self, pos, txt, align, font, pencolor,
                                txt_angle):
    x, y = pos
    x = x * self.xscale
    y = y * self.yscale
    anchor = {'left': 'sw', 'center': 's', 'right': 'se'}
    item = self.cv.create_text(
        x - 1,
        -y,
        text=txt,
        anchor=anchor[align],
        fill=pencolor,
        font=font,
        angle=txt_angle,
    )
    _, _, x1, _ = self.cv.bbox(item)
    self.cv.update()
    return item, x1 - 1
