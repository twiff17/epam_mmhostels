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


 
<!--САМ СКРИПТ ОКНА-->
    $(document).ready(function(){
        //Скрыть PopUp при загрузке страницы    
        PopUpHide(false);
    });
    //Функция отображения PopUp
    function PopUpShow(mess){
		$("#message").html(mess);
        $("#popup1").show();
		
    }
    //Функция скрытия PopUp
    function PopUpHide(reloadPage){
        $("#popup1").hide();
		if(reloadPage) {
			location.reload();
		}
    }