$(document).ready( function () {
	
    $('#myTable').dataTable({
        "oLanguage": {
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron registros ",
            "oPaginate": {
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },

        },
        "iDisplayLength": 5,
        "aLengthMenu": [
            [5, 10, 25, -1],
            [5, 10, 25, "All"]
        ],
        "aaSorting": [[0, "desc"]], // Ordenar por la primera columna en forma descendente
        "bInfo": false,
        "bLengthChange":true
    });

    var table = $('#myTable').DataTable();

    var s = $(".dataTables_wrapper .dataTables_filter");
    s.hide();

    $('#search').on('keyup', function() {
        table.search(this.value).draw();
    });

});