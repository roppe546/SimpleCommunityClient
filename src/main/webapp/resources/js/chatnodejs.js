/**
 * Created by robin on 16/12/15.
 */
var socket = io('http://130.237.84.200:3000');
$('form').submit(function() {
    socket.emit('chat message', $('#u').val() + ": " + $('#m').val());
    $('#m').val('');
    return false;
});

socket.on('chat message', function(msg) {
    $('#messages').append($('<li>').text(msg));
});