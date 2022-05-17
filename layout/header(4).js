var menu_btn = document.getElementById('nav_drop_btn');
var dropdown = document.getElementById('nav_drop_menu');

var detail_btn = document.getElementById('nav_search_detail_btn');
var detail_menu = document.getElementById('nav_search_detail');

detail_btn.addEventListener('click', function(){
    detail_menu.classList.toggle('open');
});

menu_btn.addEventListener('click', function(){
    dropdown.classList.toggle('open');
});

function local_Seoul (){
    var seoul = document.getElementById('btn_Seoul');
    var busan = document.getElementById('btn_Busan');
    
    seoul.style.display = 'block';
    busan.style.display = 'none';

    const busan_Disabled = document.getElementsByName("checkbox_busan"); //부산 지역들
    const busan_Allbtn_Disabled = document.getElementsByName("checkbox_busan_all"); //전체버튼 
    if($(busan_Disabled).prop("checked") ==true) //부산지역들이 체크되어있으면
       {
        //부산지역들의 체크를 해제
        busan_Disabled.forEach((checkbox) => 
        {
            checkbox.checked = false;
        })
       }
    if($(busan_Allbtn_Disabled).prop("checked") == true) //전체버튼이 체크되어있으면
    {
        //전체버튼의 체크 해제
        busan_Allbtn_Disabled.forEach((checkbox) => 
        {
            checkbox.checked = false;
        })
    }
}
function local_Busan()
{
    var seoul = document.getElementById('btn_Seoul'); //btn_Seoul이라는 id를 찾아서 변수 seoul에 저장
    var busan = document.getElementById('btn_Busan'); //btn_Busan이라는 id를 찾아서 변수 busan에 저장
    seoul.style.display = 'none'; //부산 버튼 클릭시에 부산의 체크박스 표시
    busan.style.display = 'block'; //부산 버튼 클릭시 서울의 체크박스 없애기
    
    /*부산 버튼 클릭시 다른 지역의 체크박스는 전부 해제 - (서울지역 해제)*/
    const seoul_Disabled = document.getElementsByName("checkbox_seoul"); //서울 지역들
    const seoul_Allbtn_Disabled = document.getElementsByName("checkbox_seoul_all"); //전체버튼 
    if($(seoul_Disabled).prop("checked") ==true) //서울지역들이 체크되어있으면
       {
        //서울지역들의 체크를 해제
        seoul_Disabled.forEach((checkbox) => 
        {
            checkbox.checked = false;
        })
       }
    if($(seoul_Allbtn_Disabled).prop("checked") == true) //전체버튼이 체크되어있으면
    {
        //전체버튼의 체크 해제
        seoul_Allbtn_Disabled.forEach((checkbox) => 
        {
            checkbox.checked = false;
        })
    }
}
function selectAll_Seoul(selectAll)  {
    const checkboxes = document.getElementsByName('checkbox_seoul'); //name이 checkbox_seoul인 객체 checkboxes에 저장
    
    //checkboxes가 없을때까지 반복(name이 checkbox_seoul인 객체들을 반복문으로 돌리는것)
    checkboxes.forEach((checkbox) => 
    {
        //name이 checkbox_seoul인 객체들의 checked 상태를 true로
        checkbox.checked = selectAll.checked
    })
}
/*서울 모든 체크박스가 선택되어있는 경우에 지역 하나를 체크해제하면 이름이 전체인 체크박스가 해제*/
function checkSelectAll_S(checkbox)  {
    const selectall = document.querySelector('input[name="checkbox_seoul_all"]');
    
    if(checkbox.checked === false)  
    {
        //name이 checkbox_seoul_selectAll인 객체들의 checked 상태를 false로
        selectall.checked = false;
    }
}

/*부산 전체 버튼 클릭시 부산 지역 체크박스 전부 선택*/
//서울의 코드와 동일
function selectAll_Busan(selectAll)  {
    const checkboxes = document.getElementsByName('checkbox_busan');
      
    checkboxes.forEach((checkbox) => 
    {
        checkbox.checked = selectAll.checked
    })
}

/*부산 모든 체크박스가 선택되어있는 경우에 지역 하나를 체크해제하면 이름이 전체인 체크박스가 해제*/
//서울의 코드와 동일
function checkSelectAll_B(checkbox)  {
    const selectall = document.querySelector('input[name="checkbox_busan_all"]');
      
    if(checkbox.checked === false)  
    {
        selectall.checked = false;
    }
}
function fnGetdata(){
    var chkArray_S = new Array(); // 배열 선언 (서울의 지역을 저장할 배열)
    var chkArray_B = new Array(); // 배열 선언 (부산의 지역을 저장할 배열)

    $('input:checkbox[name=checkbox_seoul]:checked').each(function() { // 체크된 체크박스의 value 값을 가지고 온다.
        if(this.value != "전체") //value가 전체가 아닌 경우 (전체라는 이름의 체크박스를 빼고 배열에 넣어야하기 때문에)
            chkArray_S.push(this.value);       	
    });
    
    $('input:checkbox[name=checkbox_busan]:checked').each(function() { // 체크된 체크박스의 value 값을 가지고 온다.
        if(this.value != "전체") //value가 전체가 아닌 경우 (전체라는 이름의 체크박스를 빼고 배열에 넣어야하기 때문에)
            chkArray_B.push(this.value);
    });
    
    $('input[name=arr_location_S]').val(chkArray_S); //input의 값에 chkArray_S값(배열)을 저장
    $('input[name=arr_location_B]').val(chkArray_B); //input의 값에 chkArray_B값(배열)을 저장
}
