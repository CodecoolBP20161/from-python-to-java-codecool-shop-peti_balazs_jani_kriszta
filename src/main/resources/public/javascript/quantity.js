/**
 * Created by krisztinabaranyai on 08/12/2016.
 */

function getQuantity(element){
    return parseInt($(".quantity", element).text());
}

function increaseQuantity(element){

    return (getQuantity(element) + 1).toString();
}

function reduceQuantity(element){
    var quantity = getQuantity(element);
    if (quantity < 0) {
        return "0";
    }
    return (quantity-1).toString();
}


function getAddingId() {
    return $("#add").attr("content");
}

function getMinusId() {
    return $("#reduce").attr("content");
}

function setQuantity(quantity, element){
    $(".quantity", element).text(quantity);
    $("#items-counter").text(quantity);
};

function getTotals(url) {
    return $.getJSON(url, function(response){
        setTotalPrice(response.price);
    });
}

function setTotalPrice(price){
    $("#total").text(price);
}



$(document).ready(function () {
    var QUANTITY_URL = 'http://127.0.0.1:8888/changeQuantity/';
    var DELETE_URL = 'http://127.0.0.1:8888/deleteItem/';
    var PRICE_URL = 'http://127.0.0.1:8888/totals';

    $("#add").on('click', function() {
        var quantity = increaseQuantity($(this).parent());
        var id = getAddingId();
        setQuantity(quantity, $(this).parent());
        var url = QUANTITY_URL + id + "/" + quantity;
        $.get(url);

        getTotals(PRICE_URL);

    });

    $("#reduce").on('click', function() {
        var quantity = reduceQuantity($(this).parent());
        var id = getMinusId();
        if (quantity < 0 ) {
            url = DELETE_URL + id;
            $.get(url);
        } else {
            setQuantity(quantity, $(this).parent());
            var url = QUANTITY_URL + id + "/" + quantity;
            $.get(url);
        }
    });


});







