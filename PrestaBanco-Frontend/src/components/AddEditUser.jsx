import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import SaveIcon from "@mui/icons-material/Save";
import userService from "../services/user.service";

const AddUser = () => {
  const [rut, setRut] = useState("");
  const [name, setName] = useState("");
  const [secondName, setSecondName] = useState("");
  const [lastName, setLastName] = useState("");
  const [secondLastName, setSecondLastName] = useState("");
  const [salary, setSalary] = useState("");
  const [address, setAddress] = useState("");
  const [titleUserForm, setTitleUserForm] = useState("");
  const { id } = useParams();
  const navigate = useNavigate();

  const saveUser = (u) => {
    u.preventDefault();

    const user = { rut , name , secondName , lastName , secondLastName , salary , address,id };
    if (id) {
      //Actualizar Datos usuario
      userService
        .update(user)
        .then((response) => {
          console.log("usuario actualizado correctamente", response.data);
          navigate("/user/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar actualizar datos del usuario.",
            error
          );
        });
    } else {
      //Crear nuevo usuario
      userService
        .create(user)
        .then((response) => {
          console.log("El usuario ha sido añadido.", response.data);
          navigate("/user/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar crear nuevo usuario.",
            error
          );
        });
    }
  };

  useEffect(() => {
    if (id) {
      setTitleUserForm("Editar usuario");
      userService
        .get(id)
        .then((user) => {
          setRut(user.data.rut);
          setName(user.data.name);
          setSecondName(user.data.secondName);
          setLastName(user.data.lastName);
          setSecondLastName(user.data.secondLastName);
          setSalary(user.data.salary);
          setAddress(user.data.address);
        })
        .catch((error) => {
          console.log("Se ha producido un error.", error);
        });
    } else {
      setTitleUserForm("Nuevo usuario");
    }
  }, []);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <h3> {titleUserForm} </h3>
      <hr />
      <form>
        <FormControl fullWidth>
          <TextField
            id="rut"
            label="Rut"
            value={rut}
            variant="standard"
            onChange={(u) => setRut(u.target.value)}
            helperText="Ej. 12.587.698-8"
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="name"
            label="Name"
            value={name}
            variant="standard"
            onChange={(u) => setName(u.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="secondName"
            label="Second Name"
            value={secondName}
            variant="standard"
            onChange={(u) => setSecondName(u.target.value)}  
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField  
            id="lastName"
            label="Last Name"
            value={lastName}
            variant="standard"
            onChange={(u) => setLastName(u.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="secondLastName"
            label="Second Last Name"
            value={secondLastName}
            variant="standard"
            onChange={(u) => setSecondLastName(u.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="address"
            label="Address"
            value={address}
            variant="standard"
            onChange={(u) => setAddress(u.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="salary"
            label="Salary"
            type="number"
            value={salary}
            variant="standard"
            onChange={(u) => setSalary(u.target.value)}
            helperText="Salario mensual en Pesos Chilenos"
          />
        </FormControl>

        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(u) => saveUser(u)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Grabar
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/user/list">Volver al a lista de usuarios</Link>
    </Box>
  );
};

export default AddUser;

