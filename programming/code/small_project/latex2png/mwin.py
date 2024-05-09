# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""User interface layout."""
from PyQt5 import QtCore, QtGui, QtWidgets


class UiMWin:
    """Implement the layout of interface."""

    def setup_ui(self, mwin):
        mwin.setObjectName("mwin")
        mwin.resize(750, 600)
        self.central_widget = QtWidgets.QWidget(mwin)
        self.central_widget.setObjectName("central_widget")

        # layout
        self.horizontal_layout = QtWidgets.QHBoxLayout(self.central_widget)
        self.horizontal_layout.setContentsMargins(10, 10, 10, 10)
        self.horizontal_layout.setSpacing(5)
        self.horizontal_layout.setObjectName("horizontal_layout")
        self.main_layout = QtWidgets.QGridLayout()
        self.main_layout.setSpacing(5)
        self.main_layout.setObjectName("main_layout")
        self.secondary_layout = QtWidgets.QGridLayout()
        self.secondary_layout.setSpacing(5)
        self.secondary_layout.setObjectName("secondary_layout")

        # font
        self.label_font = QtGui.QFont()
        self.label_font.setFamily("Microsoft YaHei")
        self.label_font.setPointSize(12)
        self.text_font = QtGui.QFont()
        self.text_font.setFamily("Microsoft YaHei")
        self.text_font.setPointSize(10)

        # child widgets
        self.latex_label = QtWidgets.QLabel()
        self.latex_label.setFont(self.label_font)
        self.latex_label.setAlignment(
            QtCore.Qt.AlignLeading | QtCore.Qt.AlignLeft | QtCore.Qt.AlignTop
        )
        self.latex_label.setObjectName("latex_label")
        self.main_layout.addWidget(self.latex_label, 0, 0, 1, 1)
        self.latex_text = QtWidgets.QPlainTextEdit()
        self.latex_text.setFont(self.text_font)
        self.latex_text.setObjectName("latex_text")
        self.main_layout.addWidget(self.latex_text, 0, 1, 1, 1)
        self.image_label = QtWidgets.QLabel()
        self.image_label.setFont(self.label_font)
        self.image_label.setAlignment(
            QtCore.Qt.AlignLeading | QtCore.Qt.AlignLeft | QtCore.Qt.AlignTop
        )
        self.image_label.setObjectName("image_label")
        self.main_layout.addWidget(self.image_label, 1, 0, 1, 1)
        self.png_label = QtWidgets.QLabel()
        self.png_label.setObjectName("png_label")
        self.png_label.setFixedHeight(350)
        self.main_layout.addWidget(self.png_label, 1, 1, 1, 1)

        self.width_label = QtWidgets.QLabel()
        self.width_label.setFont(self.label_font)
        self.width_label.setAlignment(
            QtCore.Qt.AlignLeading | QtCore.Qt.AlignLeft | QtCore.Qt.AlignTop
        )
        self.width_label.setObjectName("width_label")
        self.secondary_layout.addWidget(self.width_label, 0, 0, 1, 1)
        self.width_spinbox = QtWidgets.QSpinBox()
        self.width_spinbox.setRange(1, 15)
        self.width_spinbox.setSingleStep(1)
        self.width_spinbox.setValue(3)
        self.width_spinbox.setObjectName("width_spinbox")
        self.secondary_layout.addWidget(self.width_spinbox, 0, 1, 1, 1)
        self.height_label = QtWidgets.QLabel()
        self.height_label.setFont(self.label_font)
        self.height_label.setAlignment(
            QtCore.Qt.AlignLeading | QtCore.Qt.AlignLeft | QtCore.Qt.AlignTop
        )
        self.height_label.setObjectName("height_label")
        self.secondary_layout.addWidget(self.height_label, 1, 0, 1, 1)
        self.height_spinbox = QtWidgets.QSpinBox()
        self.height_spinbox.setRange(1, 3)
        self.height_spinbox.setSingleStep(1)
        self.height_spinbox.setValue(1)
        self.height_spinbox.setObjectName("height_spinbox")
        self.secondary_layout.addWidget(self.height_spinbox, 1, 1, 1, 1)
        self.generate_button = QtWidgets.QPushButton()
        self.generate_button.setObjectName("generate_button")
        self.secondary_layout.addWidget(self.generate_button, 2, 0, 1, 2)
        self.secondary_widget = QtWidgets.QWidget()
        self.secondary_widget.setObjectName("secondary_widget")
        self.secondary_widget.setLayout(self.secondary_layout)

        self.save_button = QtWidgets.QPushButton()
        self.save_button.setObjectName("save_button")
        self.main_layout.addWidget(self.save_button, 1, 2, 1, 1)

        self.main_layout.addWidget(self.secondary_widget, 0, 2, 1, 1)
        self.horizontal_layout.addLayout(self.main_layout)
        mwin.setCentralWidget(self.central_widget)

        self.retranslate_ui(mwin)

    def retranslate_ui(self, mwin):
        translate = QtCore.QCoreApplication.translate
        mwin.setWindowTitle(translate("mwin", "Latex2Png"))
        self.latex_label.setText(translate("mwin", "Latex: "))
        self.latex_text.setPlaceholderText(
            translate("mwin", "Please enter " "the LaTeX-code")
        )
        self.image_label.setText(translate("mwin", "Image: "))
        self.width_label.setText(translate("mwin", "W: "))
        self.height_label.setText(translate("mwin", "H: "))
        self.generate_button.setText(translate("mwin", "Generate"))
        self.save_button.setText(translate("mwin", "Save"))
