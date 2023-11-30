import App from './app';

import ReactDOM from "react-dom";
import AppExperiment from "./appExperiment";
import {MaterialUIControllerProvider} from "../src/context";
import {BrowserRouter} from "react-router-dom";

ReactDOM.render(
        <MaterialUIControllerProvider>
            <App/>
        </MaterialUIControllerProvider>
    , document.getElementById("root"));

const devMode = process.env.NODE_ENV === 'development';
if (devMode && module && module.hot) {
    module.hot.accept();
}