import { useEffect, useState } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import creditService from "../services/credit.service";
import userService from "../services/user.service";



const CreditListByUser = () => {
  const { userId } = useParams(); // Obtiene el ID del usuario de los parámetros de la URL
  const [credits, setCredits] = useState([]);
  const [users, setUsers] = useState([]);

  const navigate = useNavigate();



  const init = () => {  
    creditService
        .getAll()
        .then((response) => {
            console.log("Mostrando listado de todos los creditos.", response.data);
            setCredits(response.data);
        })
        .catch((error) => {
            console.log("Se ha producido un error al intentar mostrar listado de todos los creditos.", error);
        });
    }

    useEffect(() => {
        init();
    }, []);


  const getUserRut = (userId) => {
    // Llama al servicio para obtener los datos del usuario
    const fetchUser = async () => {
      try {
        const response = await userService.getById(userId);
        return response.data.rut;
      } catch (error) {
        console.error("Error al obtener los datos del usuario:", error);
      }
    };
    return fetchUser();
  }

  const handleEvaluate = () => {
    navigate("/credit/creditEvaluation");
  }

  return (
    <TableContainer component={Paper} className="mt-5">
      <h2 className="text-center">Solicitudes de Crédito del Sistema</h2>
      <Table sx={{ minWidth: 650 }} size="medium" aria-label="credit table">
        <TableHead>
          <TableRow>
          <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Rut Cliente
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Monto Solicitado
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Monto Aprobado
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Tasa de Interés
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Período de Pago (meses)
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Tipo de Crédito
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
                Fecha Creacion Crédito
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
                estado de la solicitud
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {credits.map((credit) => (
            <TableRow key={credit.id}
            sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{getUserRut(userId)}</TableCell>
              <TableCell align="left">{credit.requestedAmount}</TableCell>
              <TableCell align="left">{credit.approvedAmount}</TableCell>
              <TableCell align="left">{credit.interestRate}%</TableCell>
              <TableCell align="left">{credit.paymentPeriod} meses</TableCell>
              <TableCell align="left">{credit.creditType}</TableCell>
              <TableCell align="left">{credit.applicationDate}</TableCell>
              <TableCell align="left">{credit.status}</TableCell>
              <TableCell>
              <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleEvaluate()}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<DeleteIcon />}
                >
                  Evaluar
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Link to="/" className="btn btn-primary mt-3">
        Volver al menú Principal
      </Link>
    </TableContainer>
  );
};

export default CreditListByUser;
