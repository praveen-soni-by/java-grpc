import React from "react";
import "./App.css";
import {grpc} from "@improbable-eng/grpc-web";
import {EmptyRequest, TodoServiceClient } from './_proto/todo-service';

var service = new ()
function App() {
  

  TodoServiceClient.

  return (
    <div className="card card-container">
      <div className="card-body">
        <h5 className="card-title">Todo App</h5>
        <form>
          <div className="form-group">
            <label htmlFor="todo">Todo</label>
            <input
              type="text"
              className="form-control"
              id="todo"
              aria-describedby="todo"
              placeholder="Type Todo"
            />
          </div>

          <button type="submit" className="btn btn-primary">
            Add
          </button>
        </form>
      </div>
      <div>
        <ul className="list-group">
          <li className="list-group-item">Cras justo odio</li>
          <li className="list-group-item">Dapibus ac facilisis in</li>
          <li className="list-group-item">Morbi leo risus</li>
          <li className="list-group-item">Porta ac consectetur ac</li>
          <li className="list-group-item">Vestibulum at eros</li>
        </ul>
      </div>
    </div>
  );
}

export default App;
