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
const BarChart = require('./pages/data/BarChart.jsx');

const Moment = require('moment');
const momentLocalizer = require('react-widgets/lib/localizers/moment');
const numberLocalizer = require('react-widgets/lib/localizers/simple-number');

momentLocalizer(Moment);
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
                <ReactRouter.Router path="/data" component={BarChart} />
                <ReactRouter.Router path="/data/barchart" component={BarChart} />
        </ReactRouter.Router>
    ), document.getElementById('content'));
});
/*
 var UsersList = require('./pages/UsersList.jsx');
 var AddUser = require('./pages/AddUser.jsx');
 var EditUser = require('./pages/EditUser.jsx');


 <ReactRouter.Router path="/users/list" component={UsersList}></ReactRouter.Router>
 <ReactRouter.Router path="/users/edit" component={EditUser}></ReactRouter.Router>
 <ReactRouter.Router path="/users/add" component={AddUser}></ReactRouter.Router>

 */