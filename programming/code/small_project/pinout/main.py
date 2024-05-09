# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Draw the pinout of a CPU using JSON data."""
import json
import sys

import tkinter
import turtle

import patch_txt_angle

turtle.RawTurtle.write = patch_txt_angle.PatchTxtAngle.RawTurtleDOTwrite
# pylint: disable=protected-access
turtle.RawTurtle._write = (  # line to long
    patch_txt_angle.PatchTxtAngle.RawTurtleDOT_write
)
turtle.TurtleScreenBase._write = (  # line to long
    patch_txt_angle.PatchTxtAngle.TurtleScreenBaseDOT_write
)


def draw_name(t, name):
    t.penup()
    t.goto(10, 0)
    t.pendown()
    t.write(name, txt_angle=90, align="center", font=("Arial", 10, "bold"))


def draw_rectangle(t, rectangle_width, rectangle_height):
    t.pensize(3)
    t.penup()
    t.goto(-rectangle_width / 2, rectangle_height / 2)
    t.pendown()

    for _ in range(2):
        t.forward(rectangle_width)
        t.right(90)
        t.forward(rectangle_height)
        t.right(90)


def draw_annotation(t, annotation, rectangle_width, rectangle_height):
    t.penup()
    t.goto(rectangle_width / 2, rectangle_height / 2)
    t.pendown()
    t.write(annotation, align="left", font=("Arial", 10, "bold"))


def draw_semicircle(t, rectangle_height):
    radius = 20
    t.penup()
    t.goto(-radius, rectangle_height / 2)
    t.pendown()
    t.right(90)
    t.circle(radius, 180)
    t.right(90)


def draw_circle(t, rectangle_width, rectangle_height):
    radius = 10
    t.penup()
    t.goto(-rectangle_width / 2 + radius * 4, rectangle_height / 2 - radius * 5)
    t.pendown()
    t.circle(radius, 360)


def dip_draw_pin(t, pin_names_2d, row, col, rectangle_width, rectangle_height):
    pin_width = pin_height = 12
    t.pensize(4)
    starting_point_dip = [
        [-rectangle_width / 2, pin_width * col - 6, 0],
        [rectangle_width / 2, -rectangle_height / 2 + 12, 180],
    ]
    write_align_dip = ["right", "left"]
    write_offset_distance_dip = [[3, -3], [-14, 14]]

    for i in range(row):
        t.penup()
        t.goto(starting_point_dip[i][0], starting_point_dip[i][1])
        t.pendown()
        t.left(starting_point_dip[i][2])
        for j in range(col):
            # pin name
            t.penup()
            t.forward(-pin_width * 1.5)
            t.right(90)
            t.forward(pin_width + write_offset_distance_dip[i][0])
            t.left(90)
            t.pendown()
            t.write(
                pin_names_2d[i][j], align=write_align_dip[i], font=("Arial", 10, "bold")
            )
            t.penup()
            t.forward(pin_width * 1.5)
            t.left(90)
            t.forward(pin_width + write_offset_distance_dip[i][0])
            t.right(90)
            t.pendown()
            # small rectangle
            t.forward(-pin_width)
            t.right(90)
            t.forward(pin_height)
            t.left(90)
            t.forward(pin_width)
            t.right(90)
            t.penup()
            t.forward(pin_height)
            t.pendown()
            t.left(90)
            # number
            t.penup()
            t.forward(pin_width * 0.6)
            t.left(90)
            t.forward(pin_width + write_offset_distance_dip[i][1])
            t.right(90)
            t.pendown()
            t.write(
                str(i * col + j + 1),
                align=write_align_dip[int(not i)],
                font=("Arial", 10, "bold"),
            )
            t.penup()
            t.forward(-pin_width * 0.6)
            t.right(90)
            t.forward(pin_width + write_offset_distance_dip[i][1])
            t.left(90)
            t.pendown()


def qfp_draw_pin(t, pin_names_2d, row, col, rectangle_width, rectangle_height):
    pin_width = pin_height = 12
    t.pensize(4)
    starting_point_qfp = [
        [-rectangle_width / 2, pin_height * col - 7, 0],
        [-pin_width * col + 7, -rectangle_height / 2, 90],
        [rectangle_width / 2, -rectangle_height / 2 + 23, 90],
        [pin_width * col - 5, rectangle_height / 2, 90],
    ]
    write_align_qfp = ["right", "left"]
    write_offset_distance_qfp = [[3, -3], [4, -4], [-15, 15], [-16, 16]]
    write_txt_angle_qfp = [0, 90]

    for i in range(row):
        t.penup()
        t.goto(starting_point_qfp[i][0], starting_point_qfp[i][1])
        t.pendown()
        t.left(starting_point_qfp[i][2])
        for j in range(col):
            # pin name
            t.penup()
            t.forward(-pin_width * 1.5)
            t.right(90)
            t.forward(pin_width + write_offset_distance_qfp[i][0])
            t.left(90)
            t.pendown()
            t.write(
                pin_names_2d[i][j],
                txt_angle=write_txt_angle_qfp[i % 2],
                align=write_align_qfp[int(i / 2)],
                font=("Arial", 10, "bold"),
            )
            t.penup()
            t.forward(pin_width * 1.5)
            t.left(90)
            t.forward(pin_width + write_offset_distance_qfp[i][0])
            t.right(90)
            t.pendown()
            # small rectangle
            t.forward(-pin_width)
            t.right(90)
            t.forward(pin_height)
            t.left(90)
            t.forward(pin_width)
            t.right(90)
            t.penup()
            t.forward(pin_height)
            t.pendown()
            t.left(90)
            # number
            t.penup()
            t.forward(pin_width * 0.6)
            t.left(90)
            t.forward(pin_width + write_offset_distance_qfp[i][1])
            t.right(90)
            t.pendown()
            t.write(
                str(i * col + j + 1),
                txt_angle=write_txt_angle_qfp[i % 2],
                align=write_align_qfp[not int(i / 2)],
                font=("Arial", 10, "bold"),
            )
            t.penup()
            t.forward(-pin_width * 0.6)
            t.right(90)
            t.forward(pin_width + write_offset_distance_qfp[i][1])
            t.left(90)
            t.pendown()


def draw_cpu(data):
    cpu_name = data.get("name")
    cpu_package_type = data.get("package_type")
    annotation = data.get("annotation")

    pin_names = []
    pin_names_length = len(data.get("pin_names"))
    for i in range(pin_names_length):
        pin_names.append(data.get("pin_names").get(str(i + 1)))

    if cpu_package_type == "DIP":
        large_rectangle_width = 164
        large_rectangle_height = 12 * (pin_names_length + 1)
        row_num = 2
    elif cpu_package_type == "QFP":
        large_rectangle_width = 12 * int(pin_names_length / 2 + 3)
        large_rectangle_height = large_rectangle_width
        row_num = 4
    else:
        print("CPU package type only supports DIP or QFP type")
        sys.exit(1)

    col_num = int(pin_names_length / row_num)
    pin_names_2d = [["" for _ in range(col_num)] for _ in range(row_num)]
    for i in range(row_num):
        for j in range(col_num):
            pin_names_2d[i][j] = pin_names[i * col_num + j]

    window_width = large_rectangle_width + 160 * 2
    window_height = large_rectangle_height + 140 * 2

    root = tkinter.Tk()
    root.geometry(str(window_width) + "x" + str(window_height))
    root.title("Pinout")
    canvas = tkinter.Canvas(root, width=window_width, height=window_height)
    canvas.pack()
    t = turtle.RawTurtle(canvas)
    t.hideturtle()

    draw_name(t, cpu_name)
    draw_rectangle(t, large_rectangle_width, large_rectangle_height)
    if annotation:
        draw_annotation(t, annotation, large_rectangle_width, large_rectangle_height)
    if row_num == 2:
        draw_semicircle(t, large_rectangle_height)
        dip_draw_pin(
            t,
            pin_names_2d,
            row_num,
            col_num,
            large_rectangle_width,
            large_rectangle_height,
        )
    else:
        draw_circle(t, large_rectangle_width, large_rectangle_height)
        qfp_draw_pin(
            t,
            pin_names_2d,
            row_num,
            col_num,
            large_rectangle_width,
            large_rectangle_height,
        )

    root.mainloop()


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python3 main.py json_file_name")
        sys.exit(1)

    json_file_name = sys.argv[1]
    with open(json_file_name, "r", encoding="utf-8") as f:
        json_object = json.load(f)

    draw_cpu(json_object)
