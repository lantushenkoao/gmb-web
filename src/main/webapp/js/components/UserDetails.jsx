import React, {Component} from 'react';

export default class UserDetails extends Component{
    constructor(props){
        super(props);
        this.state =  {
            id: null,
            login: '',
            password: '',
            fullName:''
        };
    }

    componentDidMount() {
        if(this.props.loadDataUrl){
            var self = this;
            $.ajax({
                type: 'GET',
                url: self.props.loadDataUrl,
                data: {'id': self.props.userId}
            }).done(function(data){
                self.setState({
                    id: data.id,
                    login: data.login,
                    fullName: data.fullName
                });
            });
        }
    }
    handleLoginChange(e){
        this.setState({login: e.target.value})
    }
    handlePasswordChange(e){
        this.setState({password: e.target.value})
    }
    handleFullNameChange(e){
        this.setState({fullName: e.target.value})
    }
    handleSubmit(e){
        e.preventDefault();
        var id = this.state.id;
        var login = this.state.login.trim();
        var password = this.state.password.trim();
        var fullName = this.state.fullName.trim();
        if (!login || (!password && this.props.isPasswordMandatory )|| !fullName) {
            alert('Please fill all fields');
            return false;
        }
        this.props.handleSubmitCallback(id, login, password, fullName);
    }
    render(){
        return (
            <div style={{width: 200}}>
            <form id="registerForm" role="form" onSubmit={this.handleSubmit}>
                <div className="form-group"><label htmlFor="login"> Login:</label><input className="form-control" name="login" type="text" value={this.state.login}
                                onChange={this.handleLoginChange} placeholder="Login Name" required/></div>
                <div className="form-group"><label htmlFor="fullName"> Full Name:</label><input className="form-control" name="fullName" type="text" value={this.state.fullName}
                                    onChange={this.handleFullNameChange} placeholder="Full Name" required/></div>
                <div className="form-group"><label htmlFor="password"> Password: </label><input className="form-control" name="password" type="password" value={this.state.password}
                                    onChange={this.handlePasswordChange} placeholder="Password" required={this.props.isPasswordMandatory} /> </div>
                <div className="form-group">
                    <input className="form-contro btn btn-default" type="submit" value={this.props.submitButtonText} />
                </div>
            </form>
            </div>
            );
    }
};