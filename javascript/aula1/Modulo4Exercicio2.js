function User(name) {
  this.name = name;
	if (!new.target) { // if you run me without new
    return new User(name); // ...I will add new for you
  }
  // age is calculated from the current date and birthday
  Object.defineProperty(this, "age", {
    get() {
      let todayYear = new Date().getFullYear();
      return todayYear;
    }
  });
  this.sayHi = function() {
    alert( "My name is: " + this.name );
  };
}

let john = new User("John");
// same as
let user = User("Maria");

john.sayHi(); // My name is: John

user.sayHi();

console.log(john.age)
