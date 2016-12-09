/**
 * Created by krisztinabaranyai on 08/12/2016.
 */

function getQuantity(){
    return parseInt($(".quantity").text());
}

function increaseQuantity(){
    return (getQuantity() + 1).toString();
}

function reduceQuantity(){
    var quantity = getQuantity();
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

function setQuantity(quantity){
    $(".quantity").text(quantity);
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
        var quantity = increaseQuantity();
        var id = getAddingId();
        setQuantity(quantity);
        var url = QUANTITY_URL + id + "/" + quantity;
        $.get(url);

        getTotals(PRICE_URL);

    });

    $("#reduce").on('click', function() {
        var quantity = reduceQuantity();
        var id = getMinusId();
        if (quantity < 0 ) {
            url = DELETE_URL + id;
            $.get(url);
        } else {
            setQuantity(quantity);
            var url = QUANTITY_URL + id + "/" + quantity;
            $.get(url);
        }
    });


});







