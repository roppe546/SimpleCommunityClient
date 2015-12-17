/**
 * Created by robin on 17/12/15.
 */

var bus = new EventBus("http://localhost:4000/eventbus");

bus.onopen = function () {
    bus.registerHandler("chat.to.client", function (err, msg) {
        $('#m').append(msg.body + "\n");
    });
};

function send(event) {
    if (event.keyCode == 13 || event.which == 13) {

        var message = $('#u').val() + ": " + $('#m').val();

        if (message.length > 0) {
            bus.publish("chat.to.server", { "username":  $('#u').val(), "theMessage": $('#m').val() } );
            $('#m').val("");
        }
    }
}