/**
 * Created by krisztinabaranyai on 08/12/2016.
 */

function getQuantity(){
    return parseInt($(".quantity").text());
}

function increaseQuantity(){
    return quantity = (getQuantity() + 1).toString();
}

function reduceQuantity(){
    return quantity = (getQuantity() - 1).toString();
}

function setQuantity(quantity){
    $(".quantity").text(quantity);
};




$(document).ready(function () {
    $("#add").on('click', function() {
        var quantity = increaseQuantity();
        setQuantity(quantity);
    });

    $("#reduce").on('click', function() {
        var quantity = reduceQuantity();
        setQuantity(quantity);
    });

});



// var QUANTITY_URL = 'http://127.0.0.1:8888//changeQuantity/';
//
//
// function sendQuantity(id, quantity) {
//     var url = QUANTITY_URL + id + quantity;
//     $.get(url);
// }
//




