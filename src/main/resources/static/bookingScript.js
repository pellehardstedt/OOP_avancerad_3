function booking(e){
    id = e.getAttribute("data-id");

    $.ajax({
        type: 'POST',
        url: '/booking/' + id
        //data: {id: e.getAttribute("data-id") }
    });
}