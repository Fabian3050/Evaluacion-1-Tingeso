import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import simulateService from "../services/simulate.service.js";

const SimulateList = () => {
  const [simulates, setSimulates] = useState([]);
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const init = () => {
    simulateService
      .getAll()
      .then((response) => {
        console.log("Mostrando listado de todas las simulaciones.", response.data);
        setSimulates(response.data);
      })
      .catch((error) => {
        console.log(
          "Se ha producido un error al intentar mostrar listado de todas las simulaciones.",
          error
        );
      });
  };

  useEffect(() => {
    init();
  }, []);

  const handleEdit = (id) => {
    console.log("Editing simulation with id:", id);
    navigate(`/simulate/edit/${id}`);
  };

  const handleDelete = (id) => {
    const confirmDelete = window.confirm("¿Está seguro que desea borrar esta simulación?");
    if (confirmDelete) {
      simulateService
        .remove(id)
        .then((response) => {
          console.log("La simulación ha sido eliminada.", response.data);
          init();
        })
        .catch((error) => {
          console.log("Se ha producido un error al intentar eliminar la simulación", error);
        });
    }
  };

  const handleSimulate = (id) => {
    console.log("Simulating with id:", id);
    simulateService
      .simulate(id)
      .then((response) => {
        console.log("Simulación realizada correctamente.", response.data);
        setMessage(response.data.message);
        init();
      })
      .catch((error) => {
        console.log("Ha ocurrido un error al intentar realizar la simulación.", error);
      });
  }

  return (
    <TableContainer component={Paper}>
      <br />
      <Link
        to="/simulate/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<PersonAddIcon />}
        >
          Agregar simulación
        </Button>
      </Link>
      <br /> <br />
      {message && <p>{message}</p>} {/* Muestra el mensaje si existe */}
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Monthly Payment
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Loan Amount
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Interest Rate
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Payment Period
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Total Price Home
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Monthly Client Income
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Credit Type
            </TableCell>    
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Message
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Acciones
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {simulates.map((simulate) => (
            <TableRow
              key={simulate.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {simulate.monthlyPayment}
              </TableCell>
              <TableCell align="left">{simulate.loanAmount}</TableCell>
              <TableCell align="left">{simulate.interestRate}</TableCell>
              <TableCell align="left">{simulate.paymentPeriod}</TableCell>
              <TableCell align="left">{simulate.totalPriceHome}</TableCell>
              <TableCell align="left">{simulate.monthlyClientIncome}</TableCell>
              <TableCell align="left">{simulate.creditType}</TableCell>
              <TableCell align="left">{simulate.message}</TableCell>
              <TableCell align="right">
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(simulate.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<EditIcon />}
                >
                  Editar
                </Button>
                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(simulate.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<DeleteIcon />}
                >
                  Eliminar
                </Button>
                <Button>
                  variant="contained"
                  color="success"
                  size="small"
                  onClick={() => handleSimulate(simulate.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<EditIcon />}
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Link to="/">Volver al menú principal</Link>
    </TableContainer> 
  );
};

export default SimulateList;
