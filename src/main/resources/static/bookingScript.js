function booking(e){
    $.ajax({
        type: 'POST',
        url: '/booking/' + e.getAttribute("data-id")
        //data: {id: e.getAttribute("data-id") }
    });
}