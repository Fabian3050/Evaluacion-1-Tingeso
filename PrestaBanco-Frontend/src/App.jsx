import { useState } from 'react'
import './App.css'
import {BrowserRouter as Router, Route, Routes, BrowserRouter} from 'react-router-dom'
import Home from './components/Home'
import UserList from './components/UserList'
import AddUser from "./components/AddEditUser"
import SimulateList from './components/SimulateList'
import AddSimulate from './components/AddSimulate'


function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
      <div className = "container">
          <Routes>  
            <Route path="/" element={<Home />} />
            <Route path="/user/list" element = {<UserList />} />
            <Route path="/user/add" element = {<AddUser />} />
            <Route path="/user/edit/:id" element = {<AddUser />} />
            <Route path="/simulate/list" element = {<SimulateList />} />
            <Route path="/simulate/add" element = {<AddSimulate />} />
          </Routes>
      </div>
    </BrowserRouter>
  );
}
export default App
