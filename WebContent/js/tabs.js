function click_o_hotel() {
    $('#o_hotel').addClass('active');
    $('#o_ticket').removeClass('active');
    $('#start').addClass('invisible');
    $('#finish').addClass('invisible');
    $('#type').addClass('invisible');
    $('#hostelCity').removeClass('invisible');
    $('#roomCount').removeClass('invisible');
}
function click_o_ticket() {
    $('#o_hotel').removeClass('active');
    $('#o_ticket').addClass('active');
    $('#hostelCity').addClass('invisible');
    $('#roomCount').addClass('invisible');
    $('#start').removeClass('invisible');
    $('#finish').removeClass('invisible');
    $('#type').removeClass('invisible');
}

$(document).ready(function(){
	
	$("#accept-order").submit(function(e)
    		{
        var postData = $(this).serializeArray();
        $.ajax(
        {
            url : "Controller",
            type: "POST",
            data : postData,
            success:function(data, textStatus, jqXHR) 
            {
                PopUpShow(testStatus);
            },
            error: function(jqXHR, textStatus, errorThrown) 
            {
                PopUpShow(textStatus);      
            }
        });
        e.preventDefault(); //STOP default action
        e.unbind(); //unbind. to stop multiple form submit.
    });
	
    PopUpHide(false);
});
function PopUpShow(mess){
	$("#message").html(mess);
    $("#popup1").show();
}
function PopUpHide(reloadPage){
    $("#popup1").hide();
	if(reloadPage) {
		location.reload();
	}
}