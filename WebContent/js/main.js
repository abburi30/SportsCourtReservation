//var corsproxy = "https://cors-anywhere.herokuapp.com/";
var corsproxy = ""; // comment above line and uncomment below line if html is deployed on same server and port  
var backendUrl = "http://localhost:8080/ProjectTest";

var SportsReserv = {
    sports: [
        {
            id: 1,
            name: 'Football',
            img: 'img/markus-spiske-KWQ2kQtxiKE-unsplash.jpg'
        }, {
            id: 2,
            name: 'Basketball',
            img: 'img/tommy-boudreau-diO0a_ZEiEE-unsplash.jpg'
        }, {
            id: 3,
            name: 'Cricket',
            img: 'img/photo-1531415074968-036ba1b575da.jpg'
        }, {
            id: 4,
            name: 'Swimming',
            img: 'img/photo-1506322845680-6e3e1a5b1de1.jpg'
        }, {
            id: 5,
            name: 'Ping Pong',
            img: 'img/photo-1515773512591-dfaf9e052325.jpg'
        }, {
            id: 6,
            name: 'Chess',
            img: 'img/randy-fath-G1yhU1Ej-9A-unsplash.jpg'
        }],
    addSports: function () {
        $(".all-sports").empty();
        this.sports.forEach(function (val, index) {
            $(".all-sports").append(`<div class="sport" style="background-image: url('${val.img}')" data-id='${val.id}'>
                ${val.name}
            </div>`);
        });
        $('.sport').on('click', function () {
            window.location = 'sport.html?selectedSport=' + $(this).attr('data-id');
        });
    },
    updateSportsSearch: function (sports) {
        $(".all-sports").empty();
        sports.forEach(function (val, index) {
            $(".all-sports").append(`<div class="sport" style="background-image: url('${val.img}')" data-id='${val.id}'>
                ${val.name}
            </div>`);
        });
        $('.sport').on('click', function () {
            window.location = 'sport.html?selectedSport=' + $(this).attr('data-id');
        });
    },
    stadia: [
        {
            id: 101,
            name: 'Main court stadium',
            addressLine1: '500 S Maguire St.',
            city: 'Warrensburg',
            state: 'MO',
            zipCode: '77701',
            phone: '409-566-9949',
            email: 'srwc@ucmo.edu',
            img: 'img/jirayua-yaisamer-FUq3_ZCp4iU-unsplash.jpg',
            sports: [1]
        },
        {
            id: 102,
            name: 'North west court',
            addressLine1: '500 S Maguire St.',
            city: 'Warrensburg',
            state: 'MO',
            zipCode: '84083',
            phone: '435-693-7563',
            email: 'srwc@ucmo.edu',
            img: 'img/abigail-keenan-8-s5QuUBtyM-unsplash.jpg',
            sports: [1]
        },
        {
            id: 103,
            name: 'Todd open field',
            addressLine1: '500 S Maguire St.',
            city: 'Warrensburg',
            state: 'MO',
            zipCode: '75686',
            phone: '903-637-1195',
            email: 'srwc@ucmo.edu',
            img: 'img/jirayua-yaisamer-FUq3_ZCp4iU-unsplash.jpg',
            sports: [2]
        },
        {
            id: 104,
            name: 'court-2',
            addressLine1: '500 S Maguire St.',
            city: 'Warrensburg',
            state: 'MO',
            zipCode: '75686',
            phone: '903-637-1195',
            email: 'srwc@ucmo.edu',
            img: 'img/photo-1531415074968-036ba1b575da.jpg',
            sports: [2]
        },
        {
            id: 105,
            name: 'court-1',
            addressLine1: '500 S Maguire St.',
            city: 'Warrensburg',
            state: 'MO',
            zipCode: '75686',
            phone: '903-637-1195',
            email: 'srwc@ucmo.edu',
            img: 'img/photo-1531415074968-036ba1b575da.jpg',
            sports: [3]
        },
        {
            id: 106,
            name: 'Swimming court',
            addressLine1: '500 S Maguire St.',
            city: 'Warrensburg',
            state: 'MO',
            zipCode: '75686',
            phone: '903-637-1195',
            email: 'srwc@ucmo.edu',
            img: 'img/swimming.jpg',
            sports: [4]
        },
        {
            id: 107,
            name: 'court-1',
            addressLine1: '500 S Maguire St.',
            city: 'Warrensburg',
            state: 'MO',
            zipCode: '75686',
            phone: '903-637-1195',
            email: 'srwc@ucmo.edu',
            img: 'img/photo-1531415074968-036ba1b575da.jpg',
            sports: [5]
        },
        {
            id: 108,
            name: 'Court -1',
            addressLine1: '500 S Maguire St.',
            city: 'Warrensburg',
            state: 'MO',
            zipCode: '75686',
            phone: '903-637-1195',
            email: 'srwc@ucmo.edu',
            img: 'img/photo-1531415074968-036ba1b575da.jpg',
            sports: [6]
        }
    ]
}
$().ready(function () {
    SportsReserv.addSports();

    $('.search-box').keyup(function () {
        var searchText = $(this).val();
        if (searchText == '') {
            SportsReserv.addSports();
        }
        function isSportFound(sport) {
            return sport.name.toLowerCase().indexOf(searchText.toLowerCase()) > -1;
        }
        var sports = SportsReserv.sports.filter(isSportFound);
        SportsReserv.updateSportsSearch(sports);
    });
});




function gotoLoginPage() {
    window.location.href = "login.html";
}