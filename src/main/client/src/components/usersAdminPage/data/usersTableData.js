/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */
/**
=========================================================
* Material Dashboard 2 React - v2.2.0
=========================================================

* Product Page: https://www.creative-tim.com/user/material-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

 =========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
*/

// Material Dashboard 2 React components
import MDBox from "../../MDBox";
import MDTypography from "../../MDTypography";
import MDAvatar from "../../MDAvatar";
import MDBadge from "../../MDBadge";

// Images
import team2 from "../../../../assets/images/team-2.jpg";
import {useEffect, useState} from "react";
import axios from "axios";

export default function data(editFunction, users) {

  const Author = ({ image, name, email}) => (
    <MDBox display="flex" alignItems="center" lineHeight={1}>
      <MDAvatar src={image} name={name} size="sm" />
      <MDBox ml={2} lineHeight={1}>
        <MDTypography display="block" variant="button" fontWeight="medium">
          {name}
        </MDTypography>
          <MDTypography display="block" variant="button" fontWeight="text.secondary">
              {email}
          </MDTypography>
      </MDBox>
    </MDBox>
  );

  const Job = ({ title, description }) => (
    <MDBox lineHeight={1} textAlign="left">
      <MDTypography display="block" variant="caption" color="text" fontWeight="medium">
        {title}
      </MDTypography>
      <MDTypography variant="caption">{description}</MDTypography>
    </MDBox>
  );

  return {
    columns: [
      { Header: "Имя", accessor: "author", width: "45%", align: "left" },
      { Header: "Пароль", accessor: "role", align: "left" },
      { Header: "Роль", accessor: "status", align: "center" },
      { Header: "Последняя активность", accessor: "lastSeen", align: "center" },
      { Header: "", accessor: "action", align: "center" },
    ],

    rows: users.map((user)=>{
        console.log(user)
        return {
            author: <Author image={user.Img} name={user.userName} email={user.login}/>,
            role: <Job title={user.password} description="" />,
            status: (
                <MDBox ml={-1}>
                    <MDBadge badgeContent={user.role} color="success" variant="gradient" size="sm" />
                </MDBox>
            ),
            lastSeen: (
                <MDTypography component="a" href="#" variant="caption" color="text" fontWeight="medium">
                    23/04/18
                </MDTypography>
            ),
            action: (
                <MDTypography
                    component="a"
                    href="#"
                    variant="caption"
                    color="text" fontWeight="medium" onClick={(e)=>{
                    e.preventDefault()
                    editFunction(user)
                }}>
                    Редактировать
                </MDTypography>
            ),
        }
    })

  };
}
