export default class Product {
  constructor() {
    this.id = 0;
    this.title = "";
    this.description = "";
    this.price = 0;
    this.dueDate = new Date();
    this.isReleased = false;
  }
}
