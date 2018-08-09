var $message = $("#message");
var $username = $("#username");
var $send_message = $("#send_message");
var $send_username = $("#send_username");
var $chatroom = $("#chatroom");
var socket = null;
$send_message.prop("disabled", true);

function connection() {
    var inputName = $username.val();
    socket = new WebSocket('ws://localhost:8888/chat?name=' + inputName);
    $send_message.prop("disabled", false);
    $chatroom.prepend($("<p class='message'>Connected</p>"));
    socket.onerror = function (error) {
        console.log('WebSocket Error ', error)
    };
    socket.onmessage = function (event) {
        $chatroom.prepend($("<p class='message'>" + event.data + "</p>"))
    };

    socket.onclose = function (event) {
        $chatroom.prepend($("<p class='message'>" + event.data + "</p>"))
    };
}
$(document).ready(function () {
    $send_username.on('click', function () {
        if (socket) socket.close();
        connection();
    });
    $send_message.on('click', function () {
        var text = $message.val();
        $message.val("");
        socket.send(text)
    });
});


