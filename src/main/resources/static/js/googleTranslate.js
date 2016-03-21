function translateInput(){
    for(i=0; i<$('input').length; i++){
        $('input').eq(i).val($('value').eq(i).text());
    }
}