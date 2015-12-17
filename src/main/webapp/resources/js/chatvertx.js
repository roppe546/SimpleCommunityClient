/**
 * Created by robin on 17/12/15.
 */

var bus = new EventBus("http://130.237.84.200:4000/eventbus");

bus.onopen = function () {
    bus.registerHandler("chat.to.client", function (err, msg) {
        var data = JSON.parse(msg.body);
        $('#messages').append("<li>" + data.username + ": " + data.theMessage + "</li>");
    });
};

function send(event) {
    if (event.keyCode == 13 || event.which == 13) {
        if (message.length > 0) {
            bus.publish("chat.to.server", { "username":  $('#u').val(), "theMessage": $('#input').val()} );
            $('#input').val("");
        }
    }
}