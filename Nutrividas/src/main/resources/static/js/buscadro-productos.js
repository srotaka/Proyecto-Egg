$(document).ready(function () {
    $("#myInput").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#myTable tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });

  function addBasket(id) {
    var element = document.getElementById('element' + id);
    $.ajax({
      type: 'GET',
      url: '/elemento/agregar/' + id
    });
  }