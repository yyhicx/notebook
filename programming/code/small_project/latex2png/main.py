# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Main interface."""
import os
import sys
import warnings

# Close DeprecationWarning:
# Support for PyQt5 is deprecated and will be removed in Pillow 10 (2023-07-01).
warnings.filterwarnings("ignore")

# pylint: disable=wrong-import-position
from PIL import ImageQt
from PyQt5 import QtWidgets

import latex2png as lp
import mwin


class MainWindow(QtWidgets.QMainWindow, mwin.UiMWin):
    """Implement the main interface."""

    def __init__(self, parent=None):
        super().__init__(parent)
        self.setup_ui(self)
        self.cwd = os.getcwd()
        self.image = None
        self.connect_slots()

    def connect_slots(self):
        self.generate_button.clicked.connect(self.generate_image)
        self.save_button.clicked.connect(self.save_image)

    def generate_image(self):
        code = self.latex_text.toPlainText()
        width = self.width_spinbox.value()
        height = self.height_spinbox.value()
        self.image = lp.latex2png(code, (width, height), 32)
        qpixmap = ImageQt.toqpixmap(self.image)
        self.png_label.setPixmap(qpixmap)

    def save_image(self):
        def replaced(string):
            characters = ["\\", "\n", "|", " "]
            for c in characters:
                string = string.replace(c, "")
            return string

        filename = self.latex_text.toPlainText()
        filename = replaced(filename)
        dir_path = QtWidgets.QFileDialog.getExistingDirectory(
            None, "Select directory path", self.cwd
        )
        if self.image is not None and dir_path != "":
            self.image.save(os.path.join(dir_path, filename + ".png"))


if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    window = MainWindow()
    window.show()
    sys.exit(app.exec_())
