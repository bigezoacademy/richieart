
                            function showdescription() {
                                hideAllContents();
                                document.getElementById("projectdescription").style.display = "block";
                            console.log("description showing");
                            }

                            function showmaterial() {
                                hideAllContents();
                                document.getElementById("projectmaterial").style.display = "block";
                            console.log("material showing");
                            }

                            function showreviews() {
                                hideAllContents();
                                document.getElementById("projectreviews").style.display = "block";
                            }

                            function hideAllContents() {
                            document.getElementById("projectdescription").style.display = "none";
                            document.getElementById("projectmaterial").style.display = "none";
                           document.getElementById("projectreviews").style.display = "none";
                            }

