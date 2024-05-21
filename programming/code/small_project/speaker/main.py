# Copyright 2024 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Main interface."""
import argparse
import sys

import pandas as pd
from PyQt5 import QtWidgets, QtTextToSpeech

import mwin


class MainWindow(QtWidgets.QMainWindow, mwin.UiMWin):
    """Implement the main interface."""

    def __init__(self, data, parent=None):
        super().__init__(parent)
        self.setup_ui(self)
        self.data = data
        self.tts = QtTextToSpeech.QTextToSpeech()
        self.updateTable()
        self.connect_slots()

    def updateTable(self):
        self.table.setRowCount(len(self.data))
        self.table.setColumnCount(len(self.data.columns))
        self.table.setHorizontalHeaderLabels(self.data.columns)

        for i in range(len(self.data)):
            for j in range(len(self.data.columns)):
                item = QtWidgets.QTableWidgetItem(str(self.data.iloc[i, j]))
                self.table.setItem(i, j, item)

    def connect_slots(self):
        self.table.cellClicked.connect(self.updateLabel)
        self.speak_button.clicked.connect(self.speakText)

    def updateLabel(self, row, column):
        item = self.table.item(row, column)
        if item is not None:
            self.label.setText(item.text())

    def speakText(self):
        text = self.label.text()
        if text:
            self.tts.say(text)


def get_data(filename):
    with open(filename, "r", encoding="utf-8") as file:
        lines = file.readlines()
        columns = [col.strip() for col in lines[0].split("|")[1:-1]]
        data_lines = lines[2:]

        data = []
        for line in data_lines:
            line_data = [col.strip() for col in line.split("|")[1:-1]]
            data.append(line_data)

        return pd.DataFrame(data, columns=columns)


if __name__ == "__main__":
    arg_parser = argparse.ArgumentParser()
    arg_parser.add_argument("data", type=str, help="Data file")
    args = arg_parser.parse_args()
    df = get_data(args.data)
    app = QtWidgets.QApplication(sys.argv)
    window = MainWindow(df)
    window.show()
    sys.exit(app.exec_())
