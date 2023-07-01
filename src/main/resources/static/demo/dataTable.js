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

        "bInfo": false,
        "bLengthChange":false
    });

    var table = $('#myTable').DataTable();

    var s = $(".dataTables_wrapper .dataTables_filter");
    s.hide();

    $('#search').on('keyup', function() {
        table.search(this.value).draw();
    });

});