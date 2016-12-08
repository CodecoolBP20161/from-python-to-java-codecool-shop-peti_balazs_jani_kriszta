/**
 * Created by krisztinabaranyai on 08/12/2016.
 */

function getQuantity(){
    return parseInt($(".quantity").text());

}

function increaseQunatity(){
    return quantity = (getQuantity() + 1).toString();
}

function decreaseQunatity(){
    return quantity = (getQuantity() - 1).toString();
}

function setQuantity(quantity){
    $(".quantity").text(quantity);
};




$(document).ready(function () {

    $("#add").on('click', function() {
        var quantity = increaseQunatity();
        setQuantity(quantity);
    });

    $("#decrease").on('click', function() {
        var quantity = decreaseQunatity();
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




