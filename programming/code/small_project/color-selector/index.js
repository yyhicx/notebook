$(document).ready(function () {
  let oldId = "element0";

  $(".block").click(function () {
    $("#" + oldId).css("border-color", "white");
    let id = this.id;
    $("#" + id).css("border-color", "blue");
    oldId = id;
  });

  $(".input-value").keyup(function (event) {
    if (event.keyCode == 13) {
      let hex = $(this).val();
      if (hex.length > 7) {
        hex = hex.substring(0, 7);
        $(this).val(hex);
      }
      if (hex) $("#" + oldId).css("background-color", hex);
    }

    if (event.keyCode == 8) {
      if ($(this).val().length < 1) {
        $(this).val("#");
      }
    }
  });
});
