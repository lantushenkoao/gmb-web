/**
 * 3rd party components:
 * Widgets: https://jquense.github.io/react-widgets/docs/#/getting-started
 * Strings formatting: http://formatjs.io/react/#custom-formats
 */

require('es5-shim');
require('es5-shim/es5-sham');
require('console-polyfill');

const React = require('react');
const ReactDOM  = require('react-dom');
const ReactRouter = require('react-router');
const Login = require('./pages/Login.jsx');
const MyAccount = require('./pages/MyAccount.jsx');
const UsersList = require('./pages/UsersList.jsx');
const AddUser = require('./pages/AddUser.jsx');
const EditUser = require('./pages/EditUser.jsx');
const Home = require('./pages/Home.jsx');
const DataChart = require('./pages/data/DataChart.jsx');

const moment = require('moment');
const momentLocalizer = require('react-widgets/lib/localizers/moment');
const numberLocalizer = require('react-widgets/lib/localizers/simple-number');

momentLocalizer(moment);
numberLocalizer();

jQuery(document).ready(function () {

    ReactDOM.render((
        <ReactRouter.Router history={ReactRouter.hashHistory}>
                <ReactRouter.Router path="/" component={Home}/>
                <ReactRouter.Router path="/login" component={Login}/>
                <ReactRouter.Router path="/myaccount" component={MyAccount} />
                <ReactRouter.Router path="/users/list" component={UsersList} />
                <ReactRouter.Router path="/users/edit" component={EditUser} />
                <ReactRouter.Router path="/users/add" component={AddUser} />
                <ReactRouter.Router path="/data" component={DataChart} />
                <ReactRouter.Router path="/data/barchart" component={DataChart} />
        </ReactRouter.Router>
    ), document.getElementById('content'));
});