import React, {Component} from 'react';
import Header from '../components/Header.jsx';
import {Link} from 'react-router';

class UsersListHeading extends Component{
    render(){
        return (<tr>
            <th>Id</th>
            <th>Login</th>
            <th>Full Name</th>
            <th>Actions</th>
        </tr>);
    }
}

class UsersListRow extends Component {

    constructor(props){
        super(props);
    }

    handleDeleteRecord(id){
        if(!confirm('Are you sure that you want to delete this record')){
            return;
        }
        let self = this;
        $.ajax({
            type:'POST',
            url:'/api/user/delete',
            data: {
                "id": id
            }
        }).done(function(){
            self.props.handleRecordsUpdated();
        }).fail(function(xhr){
            alert('Internal error');
        });
    }
    render(){
        return (<tr>
            <td>{this.props.id}</td>
            <td>{this.props.login}</td>
            <td>{this.props.fullName}</td>
            <td>
                <input type="button" value="Delete" onClick={this.handleDeleteRecord.bind(this, this.props.id)} className="btn btn-default"/>
                <Link to={{pathname: "/users/edit", query: {userId: this.props.id}}} className="btn btn-default">Edit</Link>
            </td>
        </tr>);
    }
}

class UsersList extends Component{
    constructor(props){
        super(props)
        this.state = {users: [], canSeeCaloriesRecords: false};
    }
    componentDidMount(){
        this.reload();
    }
    reload(){
        let self = this;
        $.ajax({
            type: 'GET',
            url: '/api/user/list'
        }).done(function(data){
            self.setState({users: data});
        }).fail(function(xhr){
            alert('Internal server error');
        });
    }
    userLoadedCallback(user){
    }
    render(){
        var rows = [];
        this.state.users.forEach(function(user){
            rows.push(<UsersListRow id={user.id}
                                    key={user.id}
                                    login={user.login}
                                    fullName={user.fullName}
                                    handleRecordsUpdated={this.reload}
            />);
        }.bind(this));
        return (
            <div>
                <Header userLoadedCallback={this.userLoadedCallback}/>
                <Link to="/users/add">Create New</Link>
                <table className="table table-hover">
                    <thead><UsersListHeading /></thead>
                    <tbody>{rows}</tbody>
                </table>
            </div>
        );
    }
}

module.exports = UsersList;