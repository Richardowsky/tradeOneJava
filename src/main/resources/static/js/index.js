
$(document).ready(function() {
  console.log('select');
  $('#select-country').select2();
});

//POPUP
const $popUp = document.querySelector('.popup');
$popUp.addEventListener('click', (e)=>{
  $popUp.classList.remove('visible');
});
const popUpTrigger = document.querySelector('.radiobuttons__emphasize').addEventListener('click', (e)=>{
  $popUp.classList.add('visible');

});

// Registration page
console.log('!!!');

const trueRadioInvestor = document.querySelector('.radiobuttons__investor-true');
const falseRadioInvestor = document.querySelector('.radiobuttons__investor-false');
const trueRadioTrader = document.querySelector('.radiobuttons__trader-true');
const falseRadioTrader = document.querySelector('.radiobuttons__trader-false');

const formWrapper = document.querySelector('.form-wrapper--outer');

// ÄÂÅ ÐÀÇÍÛÅ ÔÎÐÌÛ
const loginForm = document.querySelector('.form--sign-in');
const registrationForm = document.querySelector('.form--sign-up');

// ÏÅÐÅÊËÞ×ÀÒÅËÜ ÌÅÆÄÓ ÈÍÂÅÑÒÎÐÎÌ È ÒÐÅÉÄÅÐÎÌ
registrationForm.addEventListener('click', (e) => {
  console.log('click');

if (e.target.classList.contains('radiobuttons__investor-true')) {
  falseRadioInvestor.classList.add('radiobuttons__investor-false--active');
  falseRadioTrader.classList.remove('radiobuttons__trader-false--active');
}
if (e.target.classList.contains('radiobuttons__trader-true')) {
  falseRadioTrader.classList.add('radiobuttons__trader-false--active');
  falseRadioInvestor.classList.remove('radiobuttons__investor-false--active');
}
});

const formWrapperInner = document.querySelector('.form-wrapper--inner');
const fieldsCollection = [...formWrapperInner.children];

fieldsCollection.forEach((item) => {
  if (item.classList.contains('form__field')) {
  item.isCleaned = false;
}
});


// ÐÀÁÎÒÀ Ñ ÏÎËßÌÈ ÔÎÐÌÛ
formWrapper.addEventListener('click', (e) => {

  const fieldCleaner = (field) => {
  if (field.isCleaned) {
  return;
}

field.value = '';
field.isCleaned = true;
}

if (e.target.classList.contains('form__field')) {
  fieldCleaner(e.target);

  if (e.target.name === 'password' || e.target.name === 're-password') {
    e.target.type = "password";
  }

};
});


// ÊÍÎÏÊÀ ÑÎÃËÀØÅÍÈß

const acceptanceSign = document.querySelector('.radiobuttons__acceptance');


acceptanceSign.confirm = false;

const acceptanceButton = acceptanceSign.querySelector('.radiobuttons__acceptance-checkbox');


acceptanceSign.addEventListener('click', (e) => {
  e.preventDefault();


if (!acceptanceSign.confirm) {
  acceptanceSign.confirm = true;
} else {
  acceptanceSign.confirm = false;
}

acceptanceSign.classList.toggle('radiobuttons__acceptance--active');
});

// ÑÅËÅÊÒ

const select = document.querySelector('.select');

const selectMenu = select.querySelector('.select__list');

select.addEventListener('click', (e) => {
  selectMenu.classList.toggle('hidden');

// TODO: reusable select
if (e.target.classList.contains('select__item')) {
  // console.log('this is not select');

  select.textContent = e.target.textContent;
  selectMenu.classList.add('hidden');
  select.addEventListener('click', test);
}
});

// Login page

let loginFlag = true;

const buttonsKit = document.querySelector('.buttons-kit');

const buttonsKitRegistr = document.querySelector('.buttons-kit--registr');
const buttonsKitLogin = document.querySelector('.buttons-kit--login');

const signUpButton = buttonsKitRegistr.querySelector('.button--sign-up');
const signInButton = buttonsKitLogin.querySelector('.button--sign-in');

const signUpReference = document.querySelector('.account-existance__sign-up');
const signInReference = document.querySelector('.account-existance__sign-in');

const signUpReferenceBlock = document.querySelector('.account-existance--sign-up');
const signInReferenceBlock = document.querySelector('.account-existance--sign-in');


const pageInit = () => {

  const mainTitleSignIn = document.querySelector('.main__title-sign-in');
  const mainTitleSignUp = document.querySelector('.main__title-sign-up');

  const form = document.querySelector('.form');

  const formFirst = document.querySelector('.form-wrapper-first');
  const formSecond = document.querySelector('.form-wrapper-second');

  const formFirstFields = [...formFirst.children];
  const formSecondFields = [...formSecond.children];

  const buttonsKitCollection = [...buttonsKit.querySelectorAll('.button')];

  const photoUploadButton = buttonsKit.querySelector('.button--photo');

  const buttonsKitChoice = buttonsKit.querySelector('.buttons-kit__choice');

  const acceptanceBlock = document.querySelector('.radiobuttons__wrapper--acceptance');


  if (loginFlag) {

    buttonsKitRegistr.classList.add('hidden');

    registrationForm.classList.add('hidden');

    mainTitleSignIn.classList.remove('hidden');
    mainTitleSignUp.classList.add('hidden');

    buttonsKitChoice.classList.remove('hidden');
    signUpReferenceBlock.classList.remove('hidden');

    photoUploadButton.classList.add('hidden');


    // âûðàâíèâàíèå
    formWrapper.style['margin-right'] = '0px';
    formWrapperInner.style['margin-right'] = '0px';

    // óáèðàþ êíîïêó çàãðóçêè ôîòî
    photoUploadButton.classList.add('hidden');

    // óáèðàþ áëîê ñîãëàøåíèÿ
    acceptanceBlock.classList.add('hidden');

    // ðåäàêòèðóþ áëîê êíîïîê âõîäà/ðåãèñòðàöèè
    buttonsKit.style = 'flex-direction: column; align-items: center;';
    buttonsKitChoice.style = 'transform: translateX(6px);';

    buttonsKitCollection.forEach((item) => {
      item.style = 'transform: translateX(12px)';
    if (item.classList.contains('button')) {
      item.style = 'width: 100%; transform: translateX(12px)';
    }
  });

    // ÑÑÛËÊÀ ÍÀ ÐÅÃÈÑÒÐÀÖÈÞ

    signInReferenceBlock.classList.add('hidden');


    formFirstFields.forEach((item) => {
      item.classList.add('hidden');

    if (item.name === 'password' || item.name === 'login') {
      item.classList.remove('hidden');
    }
  });

    formSecond.classList.add('hidden');

    signUpButton.classList.add('hidden');
    signInButton.classList.remove('hidden');

  } else {

    buttonsKitLogin.classList.add('hidden');
    buttonsKitRegistr.classList.remove('hidden');

    registrationForm.classList.remove('hidden');
    loginForm.classList.add('hidden');

    // ÊÍÎÏÊÀ ÇÀÃÐÓÇÊÈ ÔÎÒÎ

    // buttonsKit.style = 'flex-direction: row';
    // signUpButton.style = 'padding-top: 0px; padding-bottom: 0px;';
    // photoUploadButton.classList.remove('hidden');
    // photoUploadButton.style = 'padding-top: 0px;';


    formFirstFields.forEach((item) => {
      item.classList.add('hidden');

    if (item.name !== 'login') {
      item.classList.remove('hidden');
    } else {
      item.classList.add('hidden');
    }
  });

    formSecond.classList.remove('hidden');

    formWrapper.style['margin-right'] = '48px';
    formWrapperInner.style['margin-right'] = '48px';

    mainTitleSignIn.classList.add('hidden');
    mainTitleSignUp.classList.remove('hidden');

    signInButton.classList.add('hidden');
    signUpButton.classList.remove('hidden');

    signUpReferenceBlock.classList.add('hidden');
    buttonsKitChoice.classList.add('hidden');

    signInReferenceBlock.classList.remove('hidden');

    acceptanceBlock.classList.remove('hidden');


    formSecondFields.forEach((item) => {

      item.classList.remove('hidden');
  });


  }
}

pageInit();




signInButton.addEventListener('click', (e) => {
  signInButton.href = 'cabinet.html';
});

signUpReference.addEventListener('click', (e) => {
  e.preventDefault();

loginFlag = false;
pageInit();
});

signInReference.addEventListener('click', (e) => {
  e.preventDefault();

loginFlag = true;
pageInit();
});


////////////////////////////////////////////////

// // ÏÎËÓ×ÅÍÈÅ ÌÀÑÑÈÂÀ ÏÎËÅÉ ÐÅÃÈÑÒÐÀÖÈÈ

// const formFirst = document.querySelector('.form-wrapper-first');
// const formSecond = document.querySelector('.form-wrapper-second');

// const allFields = [...formFirst.children , ...formSecond.children];
// // console.log(allFields);

// let resultArray = [];
// allFields.forEach(item => {
//   if (item.name !== 'login') {
//     resultArray.push(item);
//   }
// });

// console.log(resultArray);

// // ÏÎËÓ×ÅÍÈÅ ÇÍÀ×ÅÍÈÉ ÏÎËÅÉ ÐÅÃÈÑÒÐÀÖÈÈ

// let valuesOfFields = [];

// resultArray.forEach(item => {
//   valuesOfFields.push(item.value);
// });

// console.log(valuesOfFields);


// http://localhost:8080/registration/add/investor  (POST)
// {
// "nickName":"sedorfer",
// "firstName":"Andrey",
// "lastName":"German",
// "password":"qwert",
// "email":"d@reck.ru",
// "year":"1991",
// "month":"11",
// "day":"4",
// "country":"Ukraine"}
// http://localhost:8080/registration/add/trader (POST)

const objData = {};

// ÐÀÁÎÒÀ Ñ ÐÀÄÈÎÁÀÒÒÀÍÀÌÈ

const radiobuttonsInvestor = document.querySelector('.radiobuttons__investor-true');
const radiobuttonsTrader = document.querySelector('.radiobuttons__trader-true');

const radioButtonsArr = [radiobuttonsInvestor, radiobuttonsTrader];
// console.log(radioButtonsArr);

radioButtonsArr.forEach(item => {
  // console.log(item);
  item.addEventListener('click', (e) => {
    // console.log('click');
    if (e.target === radiobuttonsInvestor) {
  radiobuttonsInvestor.value = 'true';
  radiobuttonsTrader.value = 'false';
  objData.role = roleDetection([radiobuttonsInvestor, radiobuttonsTrader]);
}
if (e.target === radiobuttonsTrader) {
  radiobuttonsInvestor.value = 'false';
  radiobuttonsTrader.value = 'true';
  objData.role = roleDetection([radiobuttonsInvestor, radiobuttonsTrader]);
}
});
})


const roleDetection = (roleArr) => {
  let elem = roleArr.find(item => {
    if (item.value === 'true') {
    return item.name;
  }
});

  return elem.name;
}

console.log(objData);

signUpButton.addEventListener('click', (e) => {
  // ÏÎËÓ×ÅÍÈÅ ÌÀÑÑÈÂÀ ÏÎËÅÉ ÐÅÃÈÑÒÐÀÖÈÈ
  // e.preventDefault();


  const formFirst = document.querySelector('.form-wrapper-first');
const formSecond = document.querySelector('.form-wrapper-second');

const allFields = [...formFirst.children , ...formSecond.children];
// console.log(allFields);

// let preResultArray = [];
// allFields.forEach(item => {
//   if (item.name !== 'login') {
//     preResultArray.push(item.value);
//   }
// });

// console.log(preResultArray);

// let resultArray = [];

// preResultArray.forEach(item => {
//   if (item !== undefined) {
//     // resultArray.push(item);

//   }
// });

// ÇÀÏÈÑÜ ÇÍÀ×ÅÍÈÉ ÏÎËÅÉ Â ÎÁÚÅÊÒ


// console.log(objData);

// const test = JSON.stringify(objData);
// console.log(test);

// ÇÀÏÐÎÑ

objData.username = document.querySelector('[name="username"]').value;
objData.firstName = document.querySelector('[name="first-name"]').value;
objData.lastName = document.querySelector('[name="last-name"]').value;
objData.password = document.querySelector('[name="password"]').value;
objData.email = document.querySelector('[name="email"]').value;
// objData.birthday = document.querySelector('[name="birth-date"]').value;
objData.year = '1991';
objData.month = '09';
objData.day = '20';
// objData.country = document.querySelector('[aria-label="country"]').value;
objData.country = 'Ukraine';



console.log(objData);




// const body = JSON.stringify(objData);
// const objectToSend = JSON.parse(jsonStr);
// console.log(objectToSend);

// ЗАПРОС

let url = "http://localhost:8080/registration/investor";

if (objData.role === 'trader') {
  url = "http://localhost:8080/registration/trader";
}



const xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
  console.log(xhr.readyState);
  if (xhr.readyState === 4) {
    console.log('request is complited');
  }
  if (xhr.status === 200) {
    console.log('request is successful');
    console.log(xhr.responseText);
  }
  if (xhr.status === 404) {
    console.log('ERROR');
  }
}


xhr.open("POST",url, true);

xhr.setRequestHeader("Content-type","application/json");
xhr.send(JSON.stringify(objData));
});

// ÏÎËÓ×ÅÍÈÅ ÇÍÀ×ÅÍÈÉ ÏÎËÅÉ ÐÅÃÈÑÒÐÀÖÈÈ

// let valuesOfFields = [];

// resultArray.forEach(item => {
//   if (item.classList.contains('alarm')) {
//     // console.log('radiobutton wrapper');
//     // console.log(valuesOfFields.push(item.children[0].children[0].value));
//     valuesOfFields.push(item.children[0].children[0].value);
//   }
//   valuesOfFields.push(item.value);
// });

// console.log(valuesOfFields);


////////////////////////////////////////////////////////////////
