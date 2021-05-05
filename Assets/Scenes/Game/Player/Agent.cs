using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Agent : MonoBehaviour
{
    [SerializeField] private AgentConfig agentConfig;

    [SerializeField] int _agentVariation;
    public int taxiTicketCount;
    public int busTicketCount;
    public int metroTicketCount;

    public Color color;
    public Sprite avatarSprite;
    public string agentName;


    public int agentVariation
    {
        get { return _agentVariation; }
        set
        {
            bool wasDefaultName = agentName == agentConfig.agentVariations[_agentVariation].agentName;
            _agentVariation = value;
            color = agentConfig.agentVariations[_agentVariation].color;
            avatarSprite = agentConfig.agentVariations[_agentVariation].avatar;
            if (wasDefaultName)
                agentName = agentConfig.agentVariations[_agentVariation].agentName;
        }
    }

    void Awake()
    {
        taxiTicketCount = agentConfig.taxiTickets;
        busTicketCount = agentConfig.busTickets;
        metroTicketCount = agentConfig.metroTickets;

        color = agentConfig.agentVariations[_agentVariation].color;
        color.a = 0.8f;
        avatarSprite = agentConfig.agentVariations[_agentVariation].avatar;
        agentName = agentConfig.agentVariations[_agentVariation].agentName;
    }

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }
}
