    function findAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
            var addr = '';
            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }

            $("#addre").val(addr);
            }
        }).open();
    }