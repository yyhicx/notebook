# Copyright 2024 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""User interface layout."""
from PyQt5 import QtCore, QtWidgets


class UiMWin:
  """Implement the layout of interface."""

  def setup_ui(self, mwin):
    mwin.setObjectName('mwin')
    mwin.setMinimumSize(750, 600)
    mwin.resize(750, 600)
    self.central_widget = QtWidgets.QWidget(mwin)
    self.central_widget.setObjectName('central_widget')

    self.vertical_layout = QtWidgets.QVBoxLayout(self.central_widget)
    self.vertical_layout.setContentsMargins(10, 10, 10, 10)
    self.vertical_layout.setSpacing(5)
    self.horizontal_layout = QtWidgets.QHBoxLayout()
    self.horizontal_layout.setContentsMargins(10, 0, 10, 0)
    self.horizontal_layout.setObjectName('horizontal_layout')

    self.table = QtWidgets.QTableWidget()
    self.table.setObjectName('table')
    self.vertical_layout.addWidget(self.table)
    self.label = QtWidgets.QLabel()
    self.label.setObjectName('label')
    self.horizontal_layout.addWidget(self.label)
    self.speak_button = QtWidgets.QPushButton()
    self.speak_button.setFixedWidth(80)
    self.speak_button.setObjectName('speak_button')
    self.horizontal_layout.addWidget(self.speak_button)
    self.vertical_layout.addLayout(self.horizontal_layout)
    mwin.setCentralWidget(self.central_widget)

    self.retranslate_ui(mwin)

  def retranslate_ui(self, mwin):
    translate = QtCore.QCoreApplication.translate
    mwin.setWindowTitle(translate('mwin', 'Speaker'))
    self.label.setText(
        translate('mwin', 'Selected cell content will be displayed here'))
    self.speak_button.setText(translate('mwin', 'Speak'))
