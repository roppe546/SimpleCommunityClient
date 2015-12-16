/**
 * Created by robin on 16/12/15.
 */
var socket = io('http://130.229.149.4:3000');
$('form').submit(function() {
    socket.emit('chat message', {username : $('#username').val(), message :$('#m').val()});
    $('#username').val('');
    $('#m').val('');
    return false;
});

socket.on('chat message', function(msg) {
    Console.log(msg);
    $('#messages').append($('<li>').text(msg));
});