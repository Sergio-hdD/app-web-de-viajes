$(document).ready(function() {
        	
            var ctx = document.getElementById('myPieChart').getContext('2d');
                   
            $.ajax({
                type: 'GET',
                url: "viajes/cantidadViajesEmpleados",
                dataType: 'json',
                success: function(data) {
                	if(Object.keys(data).length > 0){
                		
                		var cantidades = [];
                        var nombres = [];

                        $.each(data, function(i, item) {

                            cantidades.push(data[i].cantidad);
                            nombres.push(data[i].nombre);
                        });



                        var ctx = document.getElementById('myPieChart').getContext('2d');
                        var myChart = new Chart(ctx, {
                            type: 'doughnut',
                            data: {
                                labels: nombres,
                                datasets: [{
                                    label: '# of Votes',
                                    data: cantidades,
                                    backgroundColor: [
                                        '#fa26a0',
                                        '#05dfd7',
                                        '#f40552',
                                        '#fff591',
                                        'rgba(153, 102, 255,1)',
                                        'rgba(255, 159, 64, 1)'
                                    ],
                                    borderColor: [
                                        '#fa26a0',
                                        '#05dfd7',
                                        '#f40552',
                                        '#fff591',
                                        'rgba(153, 102, 255, 1)',
                                        'rgba(255, 159, 64, 1)'
                                    ],
                                    borderWidth: 3
                                }]
                            },
                            options: {

                                cutoutPercentage: 70,
                                legend: {
                                    position: 'bottom',
                                    labels: {
                                        pointStyle: 'circle',
                                        usePointStyle: true
                                    }
                                }

                            }
                        });
                		
                	}else{
                		$(".card-body").html("No se han registrado viajes esta semana");
                	}
                    





                },

            });


            
            
            $.ajax({
                type: 'GET',
                url: "viajes/cantidadViajesClientes",
                dataType: 'json',
                success: function(data) {
                	
                	if(Object.keys(data).length > 0){
                
                    var cantidad = [];
                    var nombre = [];

                    $.each(data, function(i, item) {
                    	                    	
                        cantidad.push(data[i].cantidad);
                        nombre.push(data[i].nombre);
                    });





                    var ctx = document.getElementById('myPieChart-1').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'doughnut',
                        data: {
                            labels: nombre,
                            datasets: [{
                                label: '# of Votes',
                                data: cantidad,
                                backgroundColor: [
                                    '#45046a',
                                    '#05dfd7',
                                    '#b5076b',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255,1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderColor: [
                                    '#45046a',
                                    '#05dfd7',
                                    '#b5076b',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderWidth: 3
                            }]
                        },
                        options: {

                            cutoutPercentage: 70,
                            legend: {
                                position: 'bottom',
                                labels: {
                                    pointStyle: 'circle',
                                    usePointStyle: true
                                }
                            }

                        }
                    });
                    
                	}
                	else{
                		$(".card-body").html("No se han registrado viajes esta semana")
                	}

                },





            });
            
          
            
            
            
            
        });