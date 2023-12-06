/**
=========================================================
* Material Dashboard 2 React - v2.2.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

 =========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
*/

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "../MDBox";
import MDTypography from "../MDTypography";

// Material Dashboard 2 React example components
import DashboardLayout from "../LayoutContainers/DashboardLayout";
import DashboardNavbar from "../Navbars/DashboardNavbar";
import DataTable from "../DataTable";

// Data
import authorsTableData from "./data/usersTableData.js";

import React, {useEffect, useState} from "react";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import CardActions from "@mui/material/CardActions";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import axios from "axios";

function UsersTable() {
  const [isEditing, setIsEditing] = useState(false);
  const [editedItem, setEditedItem] = useState({})


  const [isEditingDescr, setIsEditingDescr] = useState(false)

  const [users, setUsers] = useState([])

  useEffect(()=>{

    axios.get('http://localhost:8080/users',{
      validateStatus: function (status) {
        return status < 500;
      },
      withCredentials: true
    }).then((response)=>{
      if (response.status === 200){
        console.log(response.data)
        setUsers(response.data)
      }

    })

  }, [])

  function editFunction(item){
    setIsEditing(true)
    setIsEditingDescr(false)
    setEditedItem(item)
  }
  function handleDelete(){
    axios.delete(`http://localhost:8080/users/${editedItem.id}`,{
      validateStatus: function (status) {
        return status < 500;
      },
      withCredentials: true
    }).then((response)=>{
      if (response.status === 200){
        setIsEditing(false)
        axios.get('http://localhost:8080/users',{
          validateStatus: function (status) {
            return status < 500;
          },
          withCredentials: true
        }).then((response)=>{
          if (response.status === 200){
            console.log(response.data)
            setUsers(response.data)
          }

        })
      }

    })
  }
  function handleSubmit(e){
    e.preventDefault();

    axios.put(`http://localhost:8080/users/${editedItem.id}`,{
      id:editedItem.id,
      userName: e.target[0].value,
      login: e.target[1].value,
      password: e.target[2].value,
      role: e.target[3].value


    },{
      validateStatus: function (status) {
        return status < 500;
      },
      withCredentials: true
    }).then((response)=>{
      if (response.status === 201){
        setIsEditingDescr(false)
        setEditedItem({
          id:editedItem.id,
          userName: e.target[0].value,
          login: e.target[1].value,
          password: e.target[2].value,
          role: e.target[3].value,
          img:editedItem.img
        })
        axios.get('http://localhost:8080/users',{
          validateStatus: function (status) {
            return status < 500;
          },
          withCredentials: true
        }).then((response)=>{
          if (response.status === 200){
            console.log(response.data)
            setUsers(response.data)
          }

        })
      }


    })
  }


  const { columns, rows } = authorsTableData(editFunction, users);



  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox pt={6} pb={3}>
        <Grid container spacing={3}>
          {isEditing && <Grid item xs={4}>
            <Card>
              <MDBox
                  mx={2}
                  mt={-3}
                  py={3}
                  px={2}
                  variant="gradient"
                  bgColor="info"
                  borderRadius="lg"
                  coloredShadow="info"
              >
                <MDTypography variant="h6" color="white">
                  Редактировать
                </MDTypography>
              </MDBox>
              <MDBox pt={3}>
                <Grid container xs={12}>
                  <Card variant="outlined" sx={{
                    display:"flex",
                    flexDirection:"column",
                    p:2,
                    width:'100%'
                  }}>
                    <CardMedia
                        sx={{ height: 180,
                          width:'94%',
                          mb:3
                        }}
                        image={editedItem.Img}
                        title="green iguana"
                    />
                    <CardContent>
                      {!isEditingDescr ?
                          <>
                            <Typography gutterBottom variant="h5" component="div">
                              {editedItem.userName}
                            </Typography>
                            <Typography variant="h6" color="text.secondary" sx={{mb:1}}>
                              {editedItem.login}
                            </Typography>
                            <Typography variant="body2" color="text.secondary" sx={{width: '100%'}}>
                              {editedItem.password}
                            </Typography>
                            <Typography variant="body2" color="text.secondary" sx={{width: '100%'}}>
                              {editedItem.role}
                            </Typography>
                          </>
                          :
                          <Box component="form" noValidate sx={{ mt: 3 }} onSubmit={handleSubmit}>
                            <TextField id="filled-basic" label="Имя" variant="standard"  defaultValue={editedItem.userName} sx={{ mb: 2 }}/>
                            <TextField id="filled-basic" label="Логин" variant="standard"  defaultValue={editedItem.login} sx={{ mb: 2 }}/>
                            <TextField id="filled-basic" label="Пароль" variant="standard"  defaultValue={editedItem.password} sx={{ mb: 2 }}/>
                            <TextField id="filled-basic"
                                       sx={{width: '100%'}}
                                       label="Роль"
                                       variant="standard"
                                       defaultValue={editedItem.role}
                                       multiline
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color={"white"}
                                sx={{ mt: 3, mb: 2}}
                            >
                              Изменить
                            </Button>
                          </Box>
                      }

                    </CardContent>
                    <CardActions>
                      <Button size="small" onClick={()=>{setIsEditingDescr(!isEditingDescr)}}>{isEditingDescr ? "Отмена" : "Редактировать поля"}</Button>
                      <Button size="small" onClick={()=>{handleDelete()}}>Удалить</Button>
                    </CardActions>

                  </Card>
                </Grid>
              </MDBox>
            </Card>
          </Grid>}
          <Grid item xs={isEditing ? 8 : 12}>
            <Card>
              <MDBox
                mx={2}
                mt={-3}
                py={3}
                px={2}
                variant="gradient"
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
              >
                <MDTypography variant="h6" color="white">
                  Пользователи
                </MDTypography>
              </MDBox>
              <MDBox pt={3}>
                <DataTable
                  table={{ columns, rows }}
                  isSorted={false}
                  entriesPerPage={false}
                  showTotalEntries={false}
                  noEndBorder
                />
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
    </DashboardLayout>
  );
}

export default UsersTable;
