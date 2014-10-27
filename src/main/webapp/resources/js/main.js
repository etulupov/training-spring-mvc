$(document).ready(function () {

    $('tr').on("click", function() {
        if($(this).attr('href') !== undefined){
            document.location = $(this).attr('href');
        }
    });

    $('th').on("click", function() {
        if($(this).attr('href') !== undefined){
            document.location = $(this).attr('href');
        }
    });
});

